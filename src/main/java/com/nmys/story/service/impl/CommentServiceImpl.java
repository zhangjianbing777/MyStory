package com.nmys.story.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.mapper.CommentMapper;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.service.ICommentService;
import com.nmys.story.service.IContentService;
import com.nmys.story.utils.DateKit;
import com.nmys.story.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private IContentService contentService;

    @Override
    public int selectCommentCount() {
        return commentMapper.selectCommentCount();
    }

    @Override
    public Comments selectCommentByid(Integer id) {
        return commentMapper.selectCommentByid(id);
    }

    @Override
    public PageInfo<Comments> selectNewComments(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Comments> list = commentMapper.selectNewComments();
        PageInfo<Comments> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int saveComments(Comments comment) {
        return commentMapper.saveComment(comment);
    }

    @Override
    public boolean delCommentById(Integer id) {
        return false;
    }

    @Override
    public List<Comments> selectCommentsByAuthorId(Integer authorId) {
        return commentMapper.selectCommentsByAuthorId(authorId);
    }

    @Override
    public PageInfo<Comments> getCommentsListByContentId(Integer contentId, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        // 去掉父级评论
        List<Comments> list = commentMapper.getCommentsListByContentId(contentId, "approved");
        PageInfo<Comments> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public String insertComment(Comments comments) {

        if (null == comments) {
            return "评论对象为空";
        }
        if (StringUtils.isBlank(comments.getAuthor())) {
            comments.setAuthor("热心网友");
        }
        if (StringUtils.isNotBlank(comments.getMail()) && !TaleUtils.isEmail(comments.getMail())) {
            return "请输入正确的邮箱格式";
        }
        if (StringUtils.isBlank(comments.getContent())) {
            return "评论内容不能为空";
        }
        if (comments.getContent().length() < 5 || comments.getContent().length() > 2000) {
            return "评论字数在5-2000个字符";
        }
        if (null == comments.getCid()) {
            return "评论文章不能为空";
        }
        Contents contents = contentService.getContentById(comments.getCid());//.getContents(String.valueOf(comments.getCid()));
        if (null == contents) {
            return "不存在的文章";
        }
        comments.setOwner_id(contents.getAuthorId());
        comments.setStatus("not_audit");
        comments.setCreated(DateKit.getCurrentUnixTime());
        // 非admin用户的评论都设置为0
        comments.setAuthor_id(0);
        // 保存评论
        commentMapper.saveComment(comments);

        // 更新该文章下的评论数目
        Contents temp = new Contents();
        temp.setCid(contents.getCid());
        temp.setCommentsNum(contents.getCommentsNum() + 1);
        contentService.updateContent(temp);

        return WebConstant.SUCCESS_RESULT;
    }
}
