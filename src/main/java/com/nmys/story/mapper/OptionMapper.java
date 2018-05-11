package com.nmys.story.mapper;

import com.nmys.story.model.entity.Options;
import org.apache.ibatis.annotations.Param;

public interface OptionMapper {

    Options getOptionByName(@Param("name") String name);

}
