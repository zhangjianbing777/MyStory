package com.nmys.story.interceptor;

import com.nmys.story.constant.WebConstant;
import com.nmys.story.extension.AdminCommons;
import com.nmys.story.extension.Commons;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Options;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.IOptionService;
import com.nmys.story.service.IUserService;
import com.nmys.story.utils.IPKit;
import com.nmys.story.utils.MapCache;
import com.nmys.story.utils.TaleUtils;
import com.nmys.story.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 自定义拦截器
 * author: itachi
 * Date: 2018/5/12 下午4:30
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    private static final String USER_AGENT = "user-agent";

    @Autowired
    private IUserService userService;

    @Autowired
    private IOptionService optionService;

    private MapCache cache = MapCache.single();

    @Autowired
    private Commons commons;

    @Autowired
    private AdminCommons adminCommons;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        // 请求uri
//        String uri = request.getRequestURI();
//
//        // 获取访问用户的信息
//        logger.info("UserAgent: {}", request.getHeader(USER_AGENT));
//        // 获取来访用户的IP地址
//        logger.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));
//
//        // 查看session中是否有登录用户
//        Users user = TaleUtils.getLoginUser(request);
//        if (null == user) {
//            // 用户不走登录,首先查看cookie中是否有用户信息
//            Integer uid = TaleUtils.getCookieUid(request);
//            // 有
//            if (null != uid) {
//                // 这里还是有安全隐患,cookie是可以伪造的
//                user = userService.selectUserById(uid);
//                request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, user);
//            }
//        }
//
//        // 用户session没有,cookie也没有
//        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login") && null == user) {
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return false;
//        }
//
//        //设置get请求的token
//        if (request.getMethod().equals("GET")) {
//            // 紧凑格式的 UUID
//            String csrf_token = UUID.UU64();
//            // 默认存储30分钟
//            cache.hset(Types.CSRF_TOKEN, csrf_token, uri, 30 * 60);
//            request.setAttribute("_csrf_token", csrf_token);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Options ov = optionService.getOptionByName("site_record");
        request.setAttribute("commons", commons);//一些工具类和公共方法
        request.setAttribute("option", ov);
        request.setAttribute("adminCommons", adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
