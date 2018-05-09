package com.nmys.story.service;

import com.nmys.story.model.entity.Users;

/**
 * @author itachi
 * @Title: IUserService
 * @Description: user接口
 * @date 2018/5/8下午10:30
 */
public interface IUserService {

    /**
     * Description: 用户的登录接口
     * author: itachi
     * Date: 2018/5/8 下午10:41
     */
    Users userLogin(String username, String password);

    void saveUser(Users user);

    Users selectUserByUsername(String username);

    boolean updateUser(Users user);

}
