package com.nmys.story.mapper;

import com.nmys.story.model.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface RoleMapper {

    Role getRoleByRoleName(@Param("roleName") String roleName);

}
