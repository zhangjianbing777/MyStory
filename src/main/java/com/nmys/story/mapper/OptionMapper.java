package com.nmys.story.mapper;

import com.nmys.story.model.entity.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OptionMapper {

    Options getOptionByName(@Param("name") String name);

    List<Options> getOptions();
}
