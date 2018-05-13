package com.nmys.story.service.impl;

import com.nmys.story.mapper.MetaMapper;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.service.IMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itachi
 * @Title: MetaServiceImpl
 * @Description: 友链实现类
 * @date 2018/5/12下午7:59
 */
@Service
public class MetaServiceImpl implements IMetaService{

    @Autowired
    private MetaMapper metaMapper;


    @Override
    public List<Metas> getMetasByType(String type) {
        return metaMapper.getMetasByType(type);
    }

}
