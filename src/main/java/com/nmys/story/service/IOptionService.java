package com.nmys.story.service;

import com.nmys.story.model.entity.Options;

public interface IOptionService {

    /**
     * Description:根据名字查询配置
     * Author:70kg
     * Param [name]
     * Return com.nmys.story.model.entity.Options
     * Date 2018/5/11 13:43
     */
    Options getOptionByName(String name);

}
