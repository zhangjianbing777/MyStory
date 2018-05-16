package com.nmys.story.service;

import com.nmys.story.model.entity.Metas;

import java.util.List;

/**
 * Description: 友情链接接口
 * author: itachi
 * Date: 2018/5/12 下午6:39
 */
public interface IMetaService {

    /**
     * Description: 根据类型来获取友链
     * author: itachi
     * Date: 2018/5/12 下午6:45
     */
    List<Metas> getMetasByType(String type);

    /**
     * Description:根据id删除meta
     * Author:70kg
     * Param [id]
     * Return java.lang.String
     * Date 2018/5/14 16:06
     */
    void delMetaById(Integer id);

    /**
     * Description:根据id来获取meta
     * Author:70kg
     * Param [id]
     * Return com.nmys.story.model.entity.Metas
     * Date 2018/5/14 16:10
     */
    Metas getMetaById(Integer id);

    /**
     * Description:保存项目
     * Author:70kg
     * Param [type, cname, mid]
     * Return void
     * Date 2018/5/14 16:53
     */
    void saveMeta(String type, String cname, Integer mid);

    /**
     * Description:根据条件查metas
     * Author:70kg
     * Param [type, orderby, limit]
     * Return java.util.List<com.nmys.story.model.entity.Metas>
     * Date 2018/5/14 17:24
     */
    List<Metas> getMetaList(String type, String orderby, Integer limit);

    /**
     * Description:前台标签页使用
     * Author:70kg
     * Param [type, name]
     * Return com.nmys.story.model.entity.Metas
     * Date 2018/5/16 17:53
     */
    Metas getMeta(String type, String name);

    /**
     * Description: 保存分类或者标签
     * author: itachi
     * Date: 2018/5/16 下午10:47
     */
    void saveMetas(Integer cid, String tags, String type);
}
