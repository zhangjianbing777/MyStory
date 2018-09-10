package com.nmys.story.service.impl;

import com.nmys.story.mapper.UserRoleMapper;
import com.nmys.story.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void addRoleForUser(String userId, String roleId) {
        userRoleMapper.addRoleForUser(userId, roleId);
    }

}
