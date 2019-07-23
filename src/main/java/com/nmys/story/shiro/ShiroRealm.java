package com.nmys.story.shiro;

import com.nmys.story.constant.WebConstant;
import com.nmys.story.mapper.UserPermissionMapper;
import com.nmys.story.mapper.UserRoleMapper;
import com.nmys.story.model.entity.Permission;
import com.nmys.story.model.entity.Role;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description 登录用户的认证和授权
 *
 * @author 70KG
 * @date 2018/8/20
 */
@Slf4j(topic = "ShiroRealm")
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    /**
     * 用户认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        log.info("用户：" + userName + "进行登录认证-----ShiroRealm.doGetAuthenticationInfo");
        // 通过用户名到数据库查询用户信息
        Users user = userService.selectUserByUsername(userName);
        if (user == null) {
            log.error("用户：" + userName + "登录认证失败，原因：用户不存在");
            throw new UnknownAccountException("用户不存在或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            log.error("用户：" + userName + "登录认证失败，原因：密码错误！");
            throw new IncorrectCredentialsException("用户不存在或密码错误！");
        }
        if (WebConstant.STATUS_0.equals(user.getStatus())) {
            log.error("用户：" + userName + "登录认证失败，原因：账号已被锁定！");
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }


    /**
     * 用户授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Users user = (Users) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUsername();

        log.info("获取用户：" + userName + "的角色和权限信息-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<Role> roleList = userRoleMapper.findByUserName(userName);
        Set<String> roleSet = new HashSet<>();
        for (Role r : roleList) {
            roleSet.add(r.getName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<Permission> permissionList = userPermissionMapper.findByUserName(userName);
        Set<String> permissionSet = new HashSet<>();
        for (Permission p : permissionList) {
            permissionSet.add(p.getDescription());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

}
