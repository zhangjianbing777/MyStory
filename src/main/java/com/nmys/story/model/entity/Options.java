package com.nmys.story.model.entity;

/**
 * Description:配置选项
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/11 13:42
 */
public class Options {

    // 配置名称
    private String name;

    // 配置值
    private String value;

    // 配置描述
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}