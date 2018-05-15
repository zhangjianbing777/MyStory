package com.nmys.story.service;

import com.nmys.story.model.entity.Options;

import java.util.List;

public interface IOptionService {

    /**
     * Description:根据名字查询配置
     * Author:70kg
     * Param [name]
     * Return com.nmys.story.model.entity.Options
     * Date 2018/5/11 13:43
     */
    Options getOptionByName(String name);

    /**
     * Description:所有options
     * Author:70kg
     * Param []
     * Return java.util.List<com.nmys.story.model.entity.Options>
     * Date 2018/5/15 15:05
     */
    List<Options> getOptions();

}
