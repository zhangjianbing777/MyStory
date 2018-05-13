package com.nmys.story.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.page.Page;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;
import com.blade.validator.annotation.Valid;
import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import com.nmys.story.exception.TipException;
import com.nmys.story.extension.Commons;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.LogActions;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Logs;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.model.entity.Users;
import com.nmys.story.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

//    @Inject
//    private ContentsService contentsService;

//    @Inject
//    private MetasService metasService;

    @Inject
    private SiteService siteService;

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
                        @RequestParam(value = "limit", defaultValue = "5") int limit,
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
     * 文章编辑页面
     *
     * @param cid
     * @param request
     * @return
     */
//    @GetRoute(value = "/:cid")
//    public String editArticle(@PathParam String cid, Request request) {
//        Optional<Contents> contents = contentsService.getContents(cid);
//        if (!contents.isPresent()) {
//            return render_404();
//        }
//        request.attribute("contents", contents.get());
//        List<Metas> categories = metasService.getMetas(Types.CATEGORY);
//        request.attribute("categories", categories);
//        request.attribute("active", "article");
//        request.attribute(Types.ATTACH_URL, Commons.site_option(Types.ATTACH_URL, Commons.site_url()));
//        return "admin/article_edit";
//    }

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
     * 修改文章操作
     *
     * @return
     */
//    @PostRoute(value = "modify")
//    @JSON
//    public RestResponse modifyArticle(@Valid Contents contents) {
//        try {
//            if (null == contents || null == contents.getCid()) {
//                return RestResponse.fail("缺少参数，请重试");
//            }
//            Integer cid = contents.getCid();
//            contentsService.updateArticle(contents);
//            return RestResponse.ok(cid);
//        } catch (Exception e) {
//            String msg = "文章编辑失败";
//            if (e instanceof TipException) {
//                msg = e.getMessage();
//            } else {
//                logger.error(msg, e);
//            }
//            return RestResponse.fail(msg);
//        }
//    }

    /**
     * 删除文章操作
     *
     * @param cid
     * @param request
     * @return
     */
//    @Route(value = "delete")
//    @JSON
//    public RestResponse delete(@Param int cid, HttpServletRequest request) {
//        try {
//            contentsService.delete(cid);
//            siteService.cleanCache(Types.C_STATISTICS);
//            new Logs(LogActions.DEL_ARTICLE, cid + "", request.getRemoteAddr(), this.getUid(request)).save();
//        } catch (Exception e) {
//            String msg = "文章删除失败";
//            if (e instanceof TipException) {
//                msg = e.getMessage();
//            } else {
//                logger.error(msg, e);
//            }
//            return RestResponse.fail(msg);
//        }
//        return RestResponse.ok();
//    }
}
