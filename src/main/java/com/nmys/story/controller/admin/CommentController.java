package com.nmys.story.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.controller.BaseController;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.ICommentService;
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
@Controller
@RequestMapping("admin/comments")
public class CommentController extends BaseController {

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

}
