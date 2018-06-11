package com.nmys.story.service.impl;

import com.nmys.story.exception.TipException;
import com.nmys.story.mapper.UserMapper;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.IUserService;
import com.nmys.story.utils.TaleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itachi
 * @Title: UserServiceImpl
 * @Description: user接口实现类
 * @date 2018/5/8下午10:31
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users userLogin(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new TipException("username and password is not empty");
        }
        // 加密
        String pwd = TaleUtils.MD5encode(username + password);
        // 根据用户名和加密后的密码来查询用户是否存在,省去只校验用户名
        Users user = userMapper.getUserByUserNameAndPassword(username, pwd);
//        if (null == user){
//
//            log.error("this user is not exist!");
//            throw new TipException("this user is not exist!");
//        }
        return user;
    }

    @Override
    public void saveUser(Users user) {
        userMapper.saveUser(user);
    }

    @Override
    public Users selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public boolean updateUser(Users user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Users selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
}
