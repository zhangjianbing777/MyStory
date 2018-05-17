package com.nmys.story.controller.admin;

import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import com.nmys.story.exception.TipException;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.Statistics;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.ICommentService;
import com.nmys.story.service.IContentService;
import com.nmys.story.service.IUserService;
import com.nmys.story.service.SiteService;
import com.nmys.story.utils.TaleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Description:进入后台页面
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/9 10:10
 */
@Slf4j
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController extends BaseController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IContentService contentService;

    @Autowired
    private IUserService userService;

    /**
     * 仪表盘
     */
    @GetMapping(value = {"/", "index"})
    public String index(HttpServletRequest request) {
        // 获取最新的5条评论
        List<Comments> comments = siteService.recentComments(10);
        List<Contents> contents = siteService.getContens(Types.RECENT_ARTICLE, 10);
//        Statistics     statistics = siteService.getStatistics();
        Statistics statistics = new Statistics();
//        // 取最新的20条日志
//        Page<Logs> logsPage = new Logs().page(1, 20, "id desc");
//        List<Logs> logs     = logsPage.getRows();
//
        request.setAttribute("comments", comments);
        request.setAttribute("articles", contents);
        request.setAttribute("statistics", statistics);
//        request.attribute("logs", logs);
        return "admin/index";
    }


    /**
     * Description:个人设置
     * Author:70kg
     * Param []
     * Return java.lang.String
     * Date 2018/5/14 18:08
     */
    @GetMapping(value = "profile")
    public String profile() {
        return "admin/profile";
    }

    /**
     * 保存个人信息
     */
    @PostMapping(value = "/profile")
    @ResponseBody
    public RestResponseBo saveProfile(@RequestParam String screenName,
                                      @RequestParam String email,
                                      HttpServletRequest request,
                                      HttpSession session) {
        Users users = this.user(request);
        if (StringUtils.isNotBlank(screenName) && StringUtils.isNotBlank(email)) {
            Users temp = new Users();
            temp.setUid(users.getUid());
            temp.setScreen_name(screenName);
            temp.setEmail(email);
            userService.updateUser(temp);
            // 更新日志暂时去掉
            // logService.insertLog(LogActions.UP_INFO, GsonUtils.toJsonString(temp), request.getRemoteAddr(), this.getUid(request));

            // 更新session中的数据
            Users original= (Users)session.getAttribute(WebConstant.LOGIN_SESSION_KEY);
            original.setScreen_name(screenName);
            original.setEmail(email);
            session.setAttribute(WebConstant.LOGIN_SESSION_KEY,original);
        }
        return RestResponseBo.ok();
    }

    /**
     * Description:修改密码
     * Author:70kg
     * Param [oldPassword, password, request, session]
     * Return com.nmys.story.model.bo.RestResponseBo
     * Date 2018/5/16 14:12
     */
    @PostMapping(value = "/password")
    @ResponseBody
    public RestResponseBo upPwd(@RequestParam String oldPassword,
                                @RequestParam String password,
                                HttpServletRequest request,
                                HttpSession session) {
        Users users = this.user(request);
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(password)) {
            return RestResponseBo.fail("请确认信息输入完整");
        }

        if (!users.getPassword().equals(TaleUtils.MD5encode(users.getUsername() + oldPassword))) {
            return RestResponseBo.fail("旧密码错误");
        }
        if (password.length() < 6 || password.length() > 14) {
            return RestResponseBo.fail("请输入6-14位密码");
        }

        try {
            Users temp = new Users();
            temp.setUid(users.getUid());
            // 用户名和密码加密
            String pwd = TaleUtils.MD5encode(users.getUsername() + password);
            temp.setPassword(pwd);
            userService.updateUser(temp);

            //更新session中的数据
            Users original= (Users)session.getAttribute(WebConstant.LOGIN_SESSION_KEY);
            original.setPassword(pwd);
            session.setAttribute(WebConstant.LOGIN_SESSION_KEY,original);
            return RestResponseBo.ok();
        } catch (Exception e){
            String msg = "密码修改失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
    }

}
