package com.nmys.story.controller.admin;

import com.blade.kit.DateKit;
import com.blade.kit.EncryptKit;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Session;
import com.blade.mvc.ui.RestResponse;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import com.nmys.story.exception.TipException;
import com.nmys.story.init.TaleConst;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.LogActions;
import com.nmys.story.model.entity.Logs;
import com.nmys.story.model.entity.Users;
import com.nmys.story.model.param.LoginParam;
import com.nmys.story.service.IUserService;
import com.nmys.story.utils.TaleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录，退出
 * Created by biezhi on 2017/2/21.
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AuthController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * Description: 登录页面
     * author: itachi
     * Date: 2018/5/8 下午9:34
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam(required = false) String remeberMe,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        Integer error_count = cache.get("login_error_count");
        error_count = null == error_count ? 0 : error_count;
        try {
            Users user = userService.userLogin(username, password);
            // 将登录的user放入session中
            request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, user);

            // 若勾选记住密码，将密码缓存到cookie中
            if (StringUtils.isNotBlank(remeberMe)) {
                TaleUtils.setCookie(response, user.getUid());
            }
            if (null != error_count && error_count > 3) {
                return RestResponseBo.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }

            // 。。。。记录一下用户登录的日志

        } catch (Exception e) {
            error_count += 1;
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }


    /**
     * Description:注销登录
     * Author:70kg
     * Param [session, response, request]
     * Return void
     * Date 2018/5/10 15:09
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        session.removeAttribute(WebConstant.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConstant.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            response.sendRedirect("/admin/login");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("注销失败", e);
        }
    }

}
