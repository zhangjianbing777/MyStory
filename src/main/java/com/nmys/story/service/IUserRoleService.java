package com.nmys.story.service;

public interface IUserRoleService {

    /**
     * Description: 为用户添加角色
     * Author:70KG
     * Param [roleName]
     * Return Role
     * Date 2018/9/10 13:20
     */
    void addRoleForUser(String userId, String roleId);

}
