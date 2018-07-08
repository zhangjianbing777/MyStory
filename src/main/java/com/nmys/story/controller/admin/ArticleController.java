package com.nmys.story.controller.admin;

import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import com.nmys.story.exception.TipException;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.IContentService;
import com.nmys.story.service.IMetaService;
import com.nmys.story.utils.DateKit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 后台文章管理
 * author: itachi
 * Date: 2018/5/13 下午1:55
 */
@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private IContentService contentService;

    @Autowired
    private IMetaService metaService;


    /**
     * Description: 文章管理列表
     * author: itachi
     * Date: 2018/5/13 下午2:44
     */
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "10") int limit,
                        HttpServletRequest request) {
        // 根据特定条件来查询文章列表,type = 'post'
        PageInfo<Contents> contentsPaginator = contentService.getContentsConditions(Types.ARTICLE, page, limit);
        request.setAttribute("articles", contentsPaginator);
        return "admin/article_list";
    }

    /**
     * Description: 发布文章页面初始化
     * author: itachi
     * Date: 2018/5/13 下午2:45
     */
    @GetMapping(value = "/publish")
    public String newArticle(HttpServletRequest request) {
        List<Metas> categories = metaService.getMetasByType(Types.CATEGORY);
        request.setAttribute("categories", categories);
        return "admin/article_edit";
    }


    /**
     * Description: 文章发布
     * author: itachi
     * Date: 2018/5/13 下午3:13
     */
    @PostMapping(value = "/publish")
    @ResponseBody
    public RestResponseBo publishArticle(Contents contents, HttpServletRequest request) {
        // 登录人
        Users users = this.user(request);
        // 作者
        contents.setAuthorId(users.getUid());
        // 类型
        contents.setType(Types.ARTICLE);
        if (StringUtils.isBlank(contents.getCategories())) {
            contents.setCategories("默认分类");
        }
        String result = contentService.saveContent(contents);
        if (!WebConstant.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

    /**
     * Description:编辑文章
     * Author:70kg
     * Param [cid, request]
     * Return java.lang.String
     * Date 2018/5/17 14:49
     */
    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable String cid, HttpServletRequest request) {
        Contents contents = contentService.getContentById(Integer.parseInt(cid));
        request.setAttribute("contents", contents);
        List<Metas> categories = metaService.getMetasByType(Types.CATEGORY);
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

    /**
     * Description:修改文章
     * Author:70kg
     * Param [contents, request]
     * Return com.nmys.story.model.bo.RestResponseBo
     * Date 2018/5/17 14:55
     */
    @PostMapping(value = "/modify")
    @ResponseBody
    public RestResponseBo modifyArticle(Contents contents, HttpServletRequest request) {
        Users users = this.user(request);
        int time = DateKit.getCurrentUnixTime();
        contents.setModified(time);
        contents.setAuthorId(users.getUid());
        contents.setType(Types.ARTICLE);
        String result = "";
        boolean flag = contentService.updateContent(contents);
        if(flag){
            return RestResponseBo.ok();
        } else {
            result = "文章更新失败!";
            return RestResponseBo.fail(result);
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        // 删除文章
        String result = contentService.delContentById(cid);
        if (!WebConstant.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

}
