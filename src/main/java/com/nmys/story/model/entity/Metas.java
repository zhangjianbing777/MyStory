package com.nmys.story.model.entity;

import com.blade.jdbc.core.ActiveRecord;

/**
 * Description: 友情链接元数据
 * author: itachi
 * Date: 2018/5/12 下午6:44
 */
public class Metas extends ActiveRecord {

    // 项目主键
    private Integer mid;
    // 名称
    private String name;
    // 项目缩略名
    private String slug;
    // 项目类型
    private String type;
    // 选项描述
    private String description;
    // 项目排序
    private Integer sort;
    // 父级
    private Integer parent;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

}