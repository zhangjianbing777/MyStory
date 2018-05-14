package com.nmys.story.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

/**
 * Description:数据关系
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/14 16:25
 */
@Table(value = "t_relationships", pk = "mid")
public class Relationships extends ActiveRecord {

    // 内容主键
    private Integer cid;

    // 项目主键
    private Integer mid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}