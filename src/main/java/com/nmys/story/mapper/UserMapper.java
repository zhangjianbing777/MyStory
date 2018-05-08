package com.nmys.story.mapper;

import com.nmys.story.model.entity.Users;
import org.apache.ibatis.annotations.Param;

/**
 * Description: user mapper
 * author: itachi
 * Date: 2018/5/8 下午11:14
 */
public interface UserMapper {

    Users getUserByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

}
