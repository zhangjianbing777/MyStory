package com.nmys.story.service.impl;

import com.nmys.story.mapper.VisitMapper;
import com.nmys.story.model.entity.Visit;
import com.nmys.story.service.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements IVisitService {

    @Autowired
    private VisitMapper visitMapper;

    @Override
    public Visit getCountById(Integer id) {
        return visitMapper.getCountById(id);
    }

    @Override
    public void updateCountById(Integer id) {
        visitMapper.updateCountById(id);
    }
}
