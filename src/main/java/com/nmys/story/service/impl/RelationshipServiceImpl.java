package com.nmys.story.service.impl;

import com.nmys.story.mapper.RelationshipMapper;
import com.nmys.story.model.entity.Relationships;
import com.nmys.story.service.IRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements IRelationshipService {

    @Autowired
    private RelationshipMapper relationshipMapper;

    @Override
    public List<Relationships> getRelationshipById(Integer cid, Integer mid) {
//        if (cid != null) {
//            criteria.andCidEqualTo(cid);
//        }
//        if (mid != null) {
//            criteria.andMidEqualTo(mid);
//        }
        return relationshipMapper.getRelationshipByMid(mid);
    }

    @Override
    public void delRelationshipById(Integer cid, Integer mid) {
        relationshipMapper.delRelationshipById(cid,mid);
    }
}
