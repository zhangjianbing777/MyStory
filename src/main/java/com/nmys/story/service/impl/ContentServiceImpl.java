package com.nmys.story.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.mapper.ContentsMapper;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private ContentsMapper contentsMapper;

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
}
