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

import javax.servlet.http.HttpServletResponse;

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
//    @Route(value = "login", method = HttpMethod.GET)
    @GetMapping("/login")
    public String login() {
//        if (null != this.user()) {
//            response.redirect("/admin/index");
//            return null;
//        }
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public RestResponse doLogin(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam(required = false) String remeberMe,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        Integer error_count = cache.get("login_error_count");
        try {
            Users user = userService.userLogin(username, password);
            // 将登录的user放入session中
            request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, user);

            // 若勾选记住密码，将密码缓存到cookie中
            if (StringUtils.isNotBlank(remeberMe)) {
                TaleUtils.setCookie(response, user.getUid());
            }

            error_count = null == error_count ? 0 : error_count;
            if (null != error_count && error_count > 3) {
                return RestResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
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
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }

}
