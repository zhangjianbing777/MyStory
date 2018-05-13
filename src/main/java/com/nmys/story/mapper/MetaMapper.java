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

}
