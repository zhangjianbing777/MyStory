package com.nmys.story.controller;

import com.nmys.story.model.entity.Users;
import com.nmys.story.utils.MailUtil;
import com.nmys.story.utils.MapCache;
import com.nmys.story.utils.TaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;


/**
 * Description: 控制层的基类跳转
 * author: itachi
 * Date: 2018/5/12 下午8:08
 */
public abstract class BaseController {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private ExecutorService executorService;

    /**
     * 前台模板路径
     **/
    public static String THEME = "themes/front";

    /**
     * 后台模板路径
     **/
    public static String ADMIN = "admin";

    protected MapCache cache = MapCache.single();

    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public String render(String viewName, String location) {
        return location + "/" + viewName;
    }

    public String redirectBack(String viewName, String location) {
        return "redirect:/" + render(viewName, location);
    }

    public String redirectFront(String viewName, String location) {
        return "redirect:/" + render(viewName, location);
    }

    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    public BaseController keywords(HttpServletRequest request, String keywords) {
        request.setAttribute("keywords", keywords);
        return this;
    }

    public Users user(HttpServletRequest request) {
        return TaleUtils.getLoginUser(request);
    }

    public Integer getUid(HttpServletRequest request) {
        return this.user(request).getId();
    }

    public String render_404() {
        return "comm/error_404";
    }

    protected void sendEmail(String template, String email, String subject, Context context) {
        executorService.execute(() -> {
            mailUtil.sendEmail(template, email, subject, context);
        });
    }

}
