package com.nmys.story.controller.admin;

import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.IContentService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:页面管理
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/14 13:44
 */
@Controller()
@RequestMapping("admin/page")
public class PageController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private IContentService contentsService;

//    @Resource
//    private ILogService logService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        PageInfo<Contents> contentsPaginator = contentsService.getArticlesWithpage(1, WebConstant.MAX_POSTS);
        request.setAttribute("articles", contentsPaginator);
        return "admin/page_list";
    }

    @GetMapping(value = "new")
    public String newPage(HttpServletRequest request) {
        return "admin/page_edit";
    }

    @GetMapping(value = "/{cid}")
    public String editPage(@PathVariable Integer cid, HttpServletRequest request) {
        Contents contents = contentsService.getContentById(cid);
        request.setAttribute("contents", contents);
        return "admin/page_edit";
    }

    @PostMapping(value = "publish")
    @ResponseBody
    public RestResponseBo publishPage(@RequestParam String title,
                                      @RequestParam String content,
                                      @RequestParam String status,
                                      @RequestParam String slug,
                                      @RequestParam(required = false) Integer allowComment,
                                      @RequestParam(required = false) Integer allowPing,
                                      HttpServletRequest request) {

        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        Contents contents = new Contents();
        contents.setTitle(title);
        contents.setContent(content);
        contents.setStatus(status);
        contents.setSlug(slug);
        contents.setType(Types.PAGE);
        // 是否允许评论
        if (null != allowComment) {
            contents.setAllowComment(allowComment);
        }
        // 是否允许ping
        if (null != allowPing) {
            contents.setAllowPing(allowPing);
        }
        contents.setAuthorId(users.getId());
        String result = contentsService.saveContent(contents);
        if (!WebConstant.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

    /**
     * Description:       
     * Author:70kg  
     * Param [cid, title, content, status, slug, allowComment, allowPing, request]  
     * Return com.nmys.story.model.bo.RestResponseBo
     * Date 2018/5/17 16:08  
     */
    @PostMapping(value = "modify")
    @ResponseBody
    public RestResponseBo modifyArticle(@RequestParam Integer cid, @RequestParam String title,
                                        @RequestParam String content,
                                        @RequestParam String status, @RequestParam String slug,
                                        @RequestParam(required = false) Integer allowComment, @RequestParam(required = false) Integer allowPing, HttpServletRequest request) {

        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        Contents contents = new Contents();
        contents.setCid(cid);
        contents.setTitle(title);
        contents.setContent(content);
        contents.setStatus(status);
        contents.setSlug(slug);
        contents.setType(Types.PAGE);
        if (null != allowComment) {
            contents.setAllowComment(allowComment);
        }
        if (null != allowPing) {
            contents.setAllowPing(allowPing);
        }
        contents.setAuthorId(users.getId());
        // 更新文章
        String result = "";
        boolean flag = contentsService.updateContent(contents);
        if(flag){
            result = "SUCCESS";
        } else {
            result = "FAIL";
        }
        if (!WebConstant.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        // 删除文章
        String result = contentsService.delContentById(cid);
        // 插入日志
//        logService.insertLog(LogActions.DEL_ARTICLE, cid + "", request.getRemoteAddr(), this.getUid(request));
        if (!WebConstant.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }
}
