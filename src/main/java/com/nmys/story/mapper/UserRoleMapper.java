package com.nmys.story.mapper;

import com.nmys.story.model.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description
 *
 * @author 70KG
 * @date 2018/9/3
 */

@Component
public interface UserRoleMapper {

    List<Role> findByUserName(String userName);

}
