package com.nmys.story.mapper;

import com.nmys.story.model.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description
 *
 * @author 70KG
 * @date 2018/9/3
 */

@Component
public interface UserPermissionMapper {

    List<Permission> findByUserName(String userName);

}
