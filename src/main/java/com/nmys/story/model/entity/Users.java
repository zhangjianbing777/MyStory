package com.nmys.story.model.entity;

import lombok.Data;

/**
 * Description:用户
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/17 10:14
 */
@Data
public class Users {

    // user表主键
    private Integer uid;

    // 用户名称
    private String username;

    // 用户密码
    private String password;

    // 用户的邮箱
    private String email;

    // 用户的主页
    private String home_url;

    // 用户显示的名称
    private String screen_name;

    // 用户注册时的GMT unix时间戳
    private Integer created;

    // 最后活动时间
    private Integer activated;

    // 上次登录最后活跃时间
    private Integer logged;

    // 用户组
    private String group_name;

    /** 用户状态：0：锁定，1：正常 **/
    private String status;

}