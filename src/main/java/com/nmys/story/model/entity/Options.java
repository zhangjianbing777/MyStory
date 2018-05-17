package com.nmys.story.model.entity;

import lombok.Data;

/**
 * Description:配置选项
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/11 13:42
 */
@Data
public class Options {

    // 配置名称
    private String name;

    // 配置值
    private String value;

    // 配置描述
    private String description;

}