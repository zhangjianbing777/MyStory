package com.nmys.story.model.entity;

import lombok.Data;

/**
 * description 用户角色类
 *
 * @author 70KG
 * @date 2018/9/3
 */

@Data
public class Role extends BaseEntity{

    private Integer id;

    /** 角色名称 **/
    private String name;

    /** 角色描述 **/
    private String memo;

}
