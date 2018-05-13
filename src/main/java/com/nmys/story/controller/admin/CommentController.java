package com.nmys.story.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.controller.BaseController;
import com.nmys.story.exception.TipException;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.CommentsService;
import com.nmys.story.service.ICommentService;
import com.nmys.story.service.SiteService;
import com.nmys.story.utils.TaleUtils;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 博客后台评论管理
 * author: itachi
 * Date: 2018/5/13 下午1:18
 */
@Slf4j
@Controller
@RequestMapping("admin/comments")
public class CommentController extends BaseController {

    @Inject
    private CommentsService commentsService;

    @Inject
    private SiteService siteService;

    @Autowired
    private ICommentService commentService;

    /**
     * Description:评论管理
     * Author:70kg
     * Param [page, limit, request]
     * Return java.lang.String
     * Date 2018/5/10 14:05
     */
    @GetMapping(value = "")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int limit,
                        HttpServletRequest request) {
        // 获取登录人
        Users user = this.user(request);
        PageHelper.startPage(page,limit);
        // 查询非登录人的评论
        List<Comments> commentsList = commentService.selectCommentsByAuthorId(user.getUid());
        PageInfo<Comments> pageInfo = new PageInfo(commentsList);
        request.setAttribute("comments", pageInfo);
        return "admin/comment_list";
    }

    /**
     * 删除一条评论
     *
     * @param coid
     * @return
     */
    @PostRoute(value = "delete")
    @JSON
    public RestResponse delete(@Param Integer coid) {
        try {
            Comments comments = commentsService.byId(coid);
            if (null == comments) {
                return RestResponse.fail("不存在该评论");
            }
            commentsService.delete(coid, comments.getCid());
            siteService.cleanCache(Types.C_STATISTICS);
        } catch (Exception e) {
            String msg = "评论删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }

    @PostRoute(value = "status")
    @JSON
    public RestResponse delete(@Param Integer coid, @Param String status) {
//        try {
//            Comments comments = new Comments();
//            comments.setCoid(coid);
//            comments.setStatus(status);
//            comments.update();
//            siteService.cleanCache(Types.C_STATISTICS);
//        } catch (Exception e) {
//            String msg = "操作失败";
//            if (e instanceof TipException) {
//                msg = e.getMessage();
//            } else {
//                log.error(msg, e);
//            }
//            return RestResponse.fail(msg);
//        }
        return RestResponse.ok();
    }

    @PostRoute(value = "")
    @JSON
    public RestResponse reply(@Param Integer coid, @Param String content, HttpServletRequest request) {
        if (null == coid || StringKit.isBlank(content)) {
            return RestResponse.fail("请输入完整后评论");
        }

        if (content.length() > 2000) {
            return RestResponse.fail("请输入2000个字符以内的回复");
        }
        Comments c = commentsService.byId(coid);
        if (null == c) {
            return RestResponse.fail("不存在该评论");
        }
        Users users = this.user(request);
        content = TaleUtils.cleanXSS(content);
        content = EmojiParser.parseToAliases(content);

        Comments comments = new Comments();
        comments.setAuthor(users.getUsername());
        comments.setAuthor_id(users.getUid());
        comments.setCid(c.getCid());
        comments.setIp(request.getRemoteAddr());
        comments.setUrl(users.getHome_url());
        comments.setContent(content);
        if (StringKit.isNotBlank(users.getEmail())) {
            comments.setMail(users.getEmail());
        } else {
            comments.setMail("support@story.me");
        }
        comments.setParent(coid);
        try {
            commentsService.saveComment(comments);
            siteService.cleanCache(Types.C_STATISTICS);
            return RestResponse.ok();
        } catch (Exception e) {
            String msg = "回复失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
    }

}
