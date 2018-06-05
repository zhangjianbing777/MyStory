package com.nmys.story.mapper;

import com.nmys.story.model.entity.Visit;
import org.apache.ibatis.annotations.Param;

public interface VisitMapper {

    Visit getCountById(@Param("id") Integer id);

    void updateCountById(@Param("times") Integer times);

}
