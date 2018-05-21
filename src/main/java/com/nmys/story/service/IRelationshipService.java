package com.nmys.story.service;

import com.nmys.story.model.entity.Relationships;

import java.util.List;

public interface IRelationshipService {

    /**
     * Description:根据id搜索
     * Author:70kg
     * Param [cid, mid]
     * Return java.util.List<com.nmys.story.model.entity.Relationships>
     * Date 2018/5/14 16:30
     */
    List<Relationships> getRelationshipById(Integer cid, Integer mid);

    /**
     * Description:根据id删除关系
     * Author:70kg
     * Param [id]
     * Return void
     * Date 2018/5/14 16:47
     */
    void delRelationshipById(Integer cid, Integer mid);

    /**
     * Description: 保存关系
     * author: itachi
     * Date: 2018/5/16 下午11:13
     */
    void saveRelationship(Relationships relationships);

    /**
     * Description: 根据id来查询是否存在
     * author: itachi
     * Date: 2018/5/16 下午11:13
     */
    Long countById(Integer cid, int mid);

    void delRelationshipByCId(Integer cid);
}
