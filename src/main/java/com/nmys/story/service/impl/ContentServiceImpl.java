package com.nmys.story.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.mapper.ContentsMapper;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.service.IContentService;
import com.nmys.story.service.IMetaService;
import com.nmys.story.utils.DateKit;
import com.nmys.story.utils.TaleUtils;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private IMetaService metaService;

    @Override
    public List<Contents> getContentsByType(String type, String status) {
        return contentsMapper.getContentsByType(type, status);
    }

    @Override
    public PageInfo<Contents> getContentsByPageInfo(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        // 查询出所有文章
        List<Contents> list = contentsMapper.getContentsByType(Types.ARTICLE, Types.PUBLISH);
        PageInfo<Contents> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public boolean updateContent(Contents content) {
        return contentsMapper.updateContent(content);
    }

    @Override
    public Contents getContentById(Integer id) {
        return contentsMapper.getContentById(id);
    }

    @Override
    public Contents getContentBySlug(String slug) {
        return contentsMapper.getContentBySlug(slug);
    }

    @Override
    public PageInfo<Contents> getContentsConditions(String type, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Contents> list = contentsMapper.getContentsConditions(type);
        PageInfo<Contents> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public String saveContent(Contents contents) {

        if (null == contents) {
            return "文章对象为空";
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            return "文章标题不能为空";
        }
        if (StringUtils.isBlank(contents.getContent())) {
            return "文章内容不能为空";
        }
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConstant.MAX_TITLE_COUNT) {
            return "文章标题过长";
        }
        int contentLength = contents.getContent().length();
        if (contentLength > WebConstant.MAX_TEXT_COUNT) {
            return "文章内容过长";
        }
        if (null == contents.getAuthorId()) {
            return "请登录后发布文章";
        }
        if (StringUtils.isNotBlank(contents.getSlug())) {
            if (contents.getSlug().length() < 5) {
                return "路径太短了";
            }
            if (!TaleUtils.isPath(contents.getSlug())) {
                return "您输入的路径不合法";
            }

            // 查询该文章路径是否存在<类型和缩略名>
            long count = contentsMapper.selectContentByConditions(contents.getType(), contents.getSlug());
            if (count > 0) {
                return "该路径已经存在，请重新输入";
            }
        } else {
            contents.setSlug(null);
        }

        // 文章内容
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));
        // 初始化文章创建时间
        int time = DateKit.getCurrentUnixTime();
        contents.setCreated(time);
        contents.setModified(time);
        contents.setHits(0);
        contents.setCommentsNum(0);

        String tags = contents.getTags();
        String categories = contents.getCategories();
        // 保存文章
        contentsMapper.saveContent(contents);
        Integer cid = contents.getCid();
        // 保存标签
//        metaService.saveMetas(cid, tags, Types.TAG);
        // 保存分类
//        metaService.saveMetas(cid, categories, Types.CATEGORY);
        return WebConstant.SUCCESS_RESULT;

    }

    @Override
    public PageInfo<Contents> getArticlesWithpage(Integer page, Integer limit) {
        // limit9999
        PageHelper.startPage(page, limit);
        // 查询出所有类型为page的文章
        List<Contents> list = contentsMapper.getContentsConditions(Types.PAGE);
        PageInfo<Contents> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public String delContentById(Integer cid) {
        Contents contents = contentsMapper.getContentById(cid);
        if (null != contents) {
            contentsMapper.delContentById(cid);
            return WebConstant.SUCCESS_RESULT;
        }
        return "数据为空";
    }
}
