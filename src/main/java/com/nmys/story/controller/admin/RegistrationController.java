package com.nmys.story.controller.admin;

import com.nmys.story.constant.WebConstant;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.IUserService;
import com.nmys.story.utils.DateKit;
import com.nmys.story.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * description 后台用户注册控制器
 *
 * @author 70KG
 * @date 2018/9/10
 */
@Controller
@RequestMapping("/admin")
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private IUserService userService;

    /**
     * Description: 注册页初始化
     * Author:70KG
     * Return String
     * Date 2018/9/10 10:02
     */
//    @GetMapping("/registry")
    public String initRegist() {
        return "admin/registry";
    }

    /**
     * Description: 用户开始注册
     * Author:70KG
     * Param [username, password]
     * Return String
     * Date 2018/9/10 10:03
     */
//    @PostMapping("/registry")
//    @ResponseBody
    public RestResponseBo doRegist(@RequestParam String username, @RequestParam String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return RestResponseBo.fail("用户名或密码不能为空！");
        }
        Users users = userService.selectUserByUsername(username);
        if (null != users) {
            return RestResponseBo.fail("该用户已存在！");
        }
        try {
            String encryptpwd = MD5Utils.encrypt(username, password);
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(encryptpwd);
            user.setStatus(WebConstant.STATUS_1);
            user.setCreated(DateKit.getCurrentUnixTime());
            user.setScreen_name("MyStory普通用户");
            userService.saveUser(user);
        } catch (Exception e) {
            log.error("注册用户失败！" + e.getMessage());
            return RestResponseBo.fail("注册用户失败！");
        }
        return RestResponseBo.ok();
    }

}
