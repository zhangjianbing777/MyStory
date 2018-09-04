package com.nmys.story.controller.admin;

import com.nmys.story.controller.BaseController;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.service.IUserService;
import com.nmys.story.utils.MD5Utils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Description:登录 / 退出
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/11 9:10
 */
@Controller
@RequestMapping("/admin")
public class AuthController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

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
                                  @RequestParam(required = false) String remeber_me,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 密码MD5加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, StringUtils.isNotBlank(remeber_me));
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            // 30分钟失效
            subject.getSession().setTimeout(30 * 60 * 1000);
            return RestResponseBo.ok();
        } catch (UnknownAccountException e) {
            return RestResponseBo.fail(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return RestResponseBo.fail(e.getMessage());
        } catch (LockedAccountException e) {
            return RestResponseBo.fail(e.getMessage());
        } catch (AuthenticationException e) {
            return RestResponseBo.fail("认证失败！");
        }
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public RestResponseBo doLogin(@RequestParam String username,
//                                  @RequestParam String password,
//                                  @RequestParam(required = false) String remeber_me,
//                                  HttpServletRequest request,
//                                  HttpServletResponse response) {
//
//        Integer error_count = cache.get("login_error_count");
//        error_count = null == error_count ? 0 : error_count;
//        try {
//            Users user = userService.userLogin(username, password);
//            if (null == user) {
//                // 在缓存中记录失败次数,超过5次5分钟再试
//                error_count += 1;
//                cache.set("login_error_count", error_count, 300);
//                if (null != error_count && error_count >= 5) {
//                    return RestResponseBo.fail("您输入密码已经错误超过5次，请5分钟后尝试");
//                }
//                int times = 5 - error_count;
//                return RestResponseBo.fail("密码错误!您还有" + times + "次机会!");
//            }
//            // 将登录的user放入session中
//            request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, user);
//            // 默认超时时间为30分钟
//            // request.getSession().setMaxInactiveInterval(60);
//
//            // 若勾选记住密码，将用户的id加密以后存到cookie中
//            if (StringUtils.isNotBlank(remeber_me)) {
//                TaleUtils.setCookie(response, user.getUid());
//            }
//            if (null != error_count && error_count >= 5) {
//                return RestResponseBo.fail("您输入密码已经错误超过5次，请5分钟后尝试");
//            }
//
//            // 。。。。记录一下用户登录的日志
//
//        } catch (Exception e) {
//            error_count += 1;
//            cache.set("login_error_count", error_count, 300);
//            String msg = "登录失败";
//            if (e instanceof TipException) {
//                msg = e.getMessage();
//            } else {
//                log.error(msg, e);
//            }
//            return RestResponseBo.fail(msg);
//        }
//        return RestResponseBo.ok();
//    }


    /**
     * Description:注销登录
     * Author:70kg
     * Param [session, response, request]
     * Return void
     * Date 2018/5/10 15:09
     */
//    @RequestMapping("/logout")
//    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
//        // 清除session
//        session.removeAttribute(WebConstant.LOGIN_SESSION_KEY);
//        // 清除cookie
//        Cookie cookie = new Cookie(WebConstant.USER_IN_COOKIE, "");
//        cookie.setValue(null);
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        try {
//            response.sendRedirect("/admin/login");
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("注销失败", e);
//        }
//    }

    /**
     * Description: 重写logout方法，shiro默认跳到首页
     * author: 70KG
     * Date: 2018/8/23 下午9:07
     * From: www.nmyswls.com
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        // 如果已经登录，则跳转到管理首页
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/admin/login";
    }

}
