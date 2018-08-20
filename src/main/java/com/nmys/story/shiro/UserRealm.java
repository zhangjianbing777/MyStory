package com.nmys.story.shiro;

import com.nmys.story.model.entity.Users;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * description
 *
 * @author 70KG
 * @date 2018/8/20
 */
@Component
public class UserRealm extends AbstractUserRealm {

    @Override
    public UserRolesAndPermissions doGetGroupAuthorizationInfo(Users userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        // 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }

    @Override
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(Users userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        // 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }

}
