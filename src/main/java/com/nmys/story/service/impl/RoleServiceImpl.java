package com.nmys.story.service.impl;

import com.nmys.story.mapper.RoleMapper;
import com.nmys.story.model.entity.Role;
import com.nmys.story.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleMapper.getRoleByRoleName(roleName);
    }

}
