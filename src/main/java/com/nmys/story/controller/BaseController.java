package com.nmys.story.controller;

import com.blade.mvc.WebContext;
import com.blade.mvc.http.Request;
import com.nmys.story.model.entity.Users;
import com.nmys.story.utils.MapCache;
import com.nmys.story.utils.TaleUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by biezhi on 2017/2/21.
 */
public abstract class BaseController {

    public static String THEME = "themes/default";

    protected MapCache cache = MapCache.single();

    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    public BaseController keywords(Request request, String keywords) {
        request.attribute("keywords", keywords);
        return this;
    }

    public Users user(HttpServletRequest request) {
        return TaleUtils.getLoginUser(request);
    }

    public Integer getUid(HttpServletRequest request){
        return this.user(request).getUid();
    }

    public String render_404() {
        return "/comm/error_404";
    }

}
