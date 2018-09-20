package com.nmys.story.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.exception.TipException;
import com.nmys.story.mapper.MetaMapper;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.model.entity.Relationships;
import com.nmys.story.service.IContentService;
import com.nmys.story.service.IMetaService;
import com.nmys.story.service.IRelationshipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itachi
 * @Title: MetaServiceImpl
 * @Description: 友链实现类
 * @date 2018/5/12下午7:59
 */
@Service
public class MetaServiceImpl implements IMetaService {

    @Autowired
    private MetaMapper metaMapper;

    @Autowired
    private IRelationshipService relationshipService;

    @Autowired
    private IContentService contentService;


    @Override
    public List<Metas> getMetasByType(String type) {
        return metaMapper.getMetasByType(type);
    }

    @Override
    public void delMetaById(Integer id) {
        // 根据id查询meta
        Metas metas = metaMapper.getMetaById(id);
        if (null != metas) {
            String type = metas.getType();
            String name = metas.getName();
            metaMapper.delMetaById(id);
            // 根据id搜索
            List<Relationships> rlist = relationshipService.getRelationshipById(null, id);
            if (null != rlist) {
                for (Relationships r : rlist) {
                    // 根据文章的id来查询文章
                    Contents contents = contentService.getContentById(r.getCid());
                    if (null != contents) {
                        Contents temp = new Contents();
                        temp.setCid(r.getCid());
                        // 分类
                        if (type.equals(Types.CATEGORY)) {
                            temp.setCategories(reMeta(name, contents.getCategories()));
                        }
                        // 标签
                        if (type.equals(Types.TAG)) {
                            temp.setTags(reMeta(name, contents.getTags()));
                        }
                        contentService.updateContent(temp);
                    }
                }
            }
            relationshipService.delRelationshipById(null, id);
        }
        metaMapper.delMetaById(id);
    }

    private String reMeta(String name, String metas) {
        String[] ms = StringUtils.split(metas, ",");
        StringBuilder sbuf = new StringBuilder();
        for (String m : ms) {
            if (!name.equals(m)) {
                sbuf.append(",").append(m);
            }
        }
        if (sbuf.length() > 0) {
            return sbuf.substring(1);
        }
        return "";
    }

    @Override
    public Metas getMetaById(Integer id) {
        return metaMapper.getMetaById(id);
    }

    @Override
    public void saveMeta(String type, String name, Integer mid) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
            // 根据类型和名字来查询meta
            List<Metas> metaVos = metaMapper.selectMetaListByConditions(type, name);
            Metas metas;
            if (metaVos.size() != 0) {
                throw new TipException("已经存在该项");
            } else {
                metas = new Metas();
                metas.setName(name);
                if (null != mid) {
                    // mid不为空,根据mid查询meta
                    Metas original = metaMapper.getMetaById(mid);
                    metas.setMid(mid);
                    // 更新meta
                    metaMapper.updateMeta(metas);
                    // 更新原有文章的categories   -----  ?????????
//                    contentService.updateCategory(original.getName(), name);
                } else {
                    metas.setType(type);
                    // 保存meta
                    metaMapper.saveMeta(metas);
                }
            }
        }
    }

    @Override
    public List<Metas> getMetaList(String type, String orderby, Integer limit) {
        PageHelper.startPage(1, limit);
        List<Metas> list = metaMapper.getMetasBySql(type);
        PageInfo<Metas> pageInfo = new PageInfo(list);
        return pageInfo.getList();
    }

    @Override
    public Metas getMeta(String type, String name) {
        return metaMapper.getMeta(type, name);
    }

    @Override
    public void saveMetas(Integer cid, String names, String type) {
        if (null == cid) {
            throw new TipException("项目关联id不能为空");
        }
        if (StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)) {
            String[] nameArr = StringUtils.split(names, ",");
            for (String name : nameArr) {
                this.saveOrUpdate(cid, name, type);
            }
        }
    }

    private void saveOrUpdate(Integer cid, String name, String type) {
        // 根据类型和名称查询metas
        List<Metas> metaVos = metaMapper.selectMetaListByConditions(type, name);
        int mid;
        Metas metas;
        if (metaVos.size() == 1) {
            metas = metaVos.get(0);
            mid = metas.getMid();
        } else if (metaVos.size() > 1) {
            throw new TipException("查询到多条数据");
        } else {
            metas = new Metas();
            metas.setSlug(name);
            metas.setName(name);
            metas.setType(type);
            metaMapper.saveMeta(metas);
            mid = metas.getMid();
        }
        if (mid != 0) {
            Long count = relationshipService.countById(cid, mid);
            if (count == 0) {
                Relationships relationships = new Relationships();
                relationships.setCid(cid);
                relationships.setMid(mid);
                relationshipService.saveRelationship(relationships);
            }
        }
    }

}
