package com.nmys.story.interceptor;

import com.nmys.story.extension.AdminCommons;
import com.nmys.story.extension.Commons;
import com.nmys.story.model.entity.Options;
import com.nmys.story.service.IOptionService;
import com.nmys.story.service.IUserService;
import com.nmys.story.utils.IPKit;
import com.nmys.story.utils.MapCache;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j(topic = "BaseInterceptor")
public class BaseInterceptor implements HandlerInterceptor {

    private static final String USER_AGENT = "user-agent";

    @Autowired
    private IOptionService optionService;

    @Autowired
    private Commons commons;

    @Autowired
    private AdminCommons adminCommons;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 请求uri
        String uri = request.getRequestURI();
        // 获取访问用户的信息
        log.info("UserAgent: {}", request.getHeader(USER_AGENT));
        // 获取来访用户的IP地址
        log.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Options ov = optionService.getOptionByName("site_record");
        request.setAttribute("commons", commons);
        request.setAttribute("option", ov);
        request.setAttribute("adminCommons", adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
