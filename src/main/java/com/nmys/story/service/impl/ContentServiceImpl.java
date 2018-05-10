package com.nmys.story.service.impl;

import com.nmys.story.mapper.ContentsMapper;
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
}
