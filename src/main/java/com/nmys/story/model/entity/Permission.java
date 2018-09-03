package com.nmys.story.model.entity;

import lombok.Data;

/**
 * description 用户权限类
 *
 * @author 70KG
 * @date 2018/9/3
 */

@Data
public class Permission extends BaseEntity {

    private Integer id;

    /** url地址 **/
    private String url;

    /** url描述 **/
    private String name;

}
