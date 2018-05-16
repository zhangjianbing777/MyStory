package com.nmys.story.mapper;

import com.nmys.story.model.entity.Metas;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaMapper {

    /**
     * Description: 根据类型来查询友链
     * author: itachi
     * Date: 2018/5/12 下午8:05
     */
    List<Metas> getMetasByType(@Param("type") String type);

    /**
     * Description:更新meta
     * Author:70kg
     * Param [metas]
     * Return void
     * Date 2018/5/14 12:37
     */
    void updateMeta(Metas metas);

    /**
     * Description:新增meta
     * Author:70kg
     * Param [metas]
     * Return void
     * Date 2018/5/14 12:37
     */
    void saveMeta(Metas metas);

    /**
     * Description:根据id删除meta
     * Author:70kg
     * Param [id]
     * Return void
     * Date 2018/5/14 12:37
     */
    void delMetaById(@Param("id") Integer id);

    /**
     * Description:根据id来查询meta
     * Author:70kg
     * Param [id]
     * Return com.nmys.story.model.entity.Metas
     * Date 2018/5/14 16:12
     */
    Metas getMetaById(@Param("id") Integer id);

    /**
     * Description:根据条件来查询meta
     * Author:70kg
     * Param [type, name]
     * Return java.util.List<com.nmys.story.model.entity.Metas>
     * Date 2018/5/14 16:57
     */
    List<Metas> selectMetaListByConditions(@Param("type") String type, @Param("name") String name);

    /**
     * Description:meta和relationship的联合查询
     * Author:70kg
     * Param [type]
     * Return java.util.List<com.nmys.story.model.entity.Metas>
     * Date 2018/5/14 17:34
     */
    List<Metas> getMetasBySql(@Param("type") String type);

    /**
     * Description:前台标签页使用
     * Author:70kg
     * Param [type, name]
     * Return com.nmys.story.model.entity.Metas
     * Date 2018/5/16 17:55
     */
    Metas getMeta(@Param("type") String type, @Param("name") String name);

    /**
     * Description:查询标签下的文章数量
     * Author:70kg
     * Param [mid]
     * Return int
     * Date 2018/5/16 18:28
     */
    int countWithSql(Integer mid);
}
