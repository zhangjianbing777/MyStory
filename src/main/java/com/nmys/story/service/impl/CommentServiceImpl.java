package com.nmys.story.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.mapper.CommentMapper;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

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
}
