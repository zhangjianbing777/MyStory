package com.nmys.story.controller;

import com.nmys.story.model.entity.Users;
import com.nmys.story.utils.MapCache;
import com.nmys.story.utils.TaleUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * Description: 控制层的基类跳转
 * author: itachi
 * Date: 2018/5/12 下午8:08
 */
public abstract class BaseController {

    public static String THEME = "themes/front";

    protected MapCache cache = MapCache.single();

    public String render(String viewName) {
        return THEME + "/" + viewName;
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

    public Integer getUid(HttpServletRequest request){
        return this.user(request).getId();
    }

    public String render_404() {
        return "comm/error_404";
    }

}
