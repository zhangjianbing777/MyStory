package com.nmys.story.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.OrderBy;
import com.blade.jdbc.page.Page;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.Session;
import com.blade.mvc.ui.RestResponse;
import com.blade.security.web.csrf.CsrfToken;
import com.blade.validator.annotation.Valid;
import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.exception.TipException;
import com.nmys.story.extension.Commons;
import com.nmys.story.init.TaleConst;
import com.nmys.story.model.dto.Archive;
import com.nmys.story.model.dto.ErrorCode;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.service.*;
import com.nmys.story.utils.TaleUtils;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * Description:博客首页控制层
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/11 9:21
 */
@Slf4j
@Controller
public class IndexController extends BaseController {

    @Inject
    private ContentsService contentsService;

    @Inject
    private MetasService metasService;

    @Inject
    private CommentsService commentsService;

    @Inject
    private SiteService siteService;

    @Autowired
    private IContentService contentService;

    /**
     * Description:博客首页
     * Author:70kg
     * Param [request, limit]
     * Return java.lang.String
     * Date 2018/5/11 9:22
     */
    @GetMapping(value = "/")
    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.index(request, 1, limit);
    }

    /**
     * 自定义页面
     */
    @CsrfToken(newToken = true)
    @GetRoute(value = {"/:cid", "/:cid.html"})
    public String page(@PathParam String cid, Request request) {
        Optional<Contents> contentsOptional = contentsService.getContents(cid);
        if (!contentsOptional.isPresent()) {
            return this.render_404();
        }
        Contents contents = contentsOptional.get();
        if (contents.getAllowComment()) {
            int cp = request.queryInt("cp", 1);
            request.attribute("cp", cp);
        }
        request.attribute("article", contents);
        Contents temp = new Contents();
        temp.setHits(contents.getHits() + 1);
        temp.setCid(contents.getCid());
//        temp.update(contents.getCid());
        contentService.updateContent(temp);
        if (Types.ARTICLE.equals(contents.getType())) {
            return this.render("post");
        }
        if (Types.PAGE.equals(contents.getType())) {
            return this.render("page");
        }
        return this.render_404();
    }

    /**
     * Description:博客首页分页
     * Author:70kg
     * Param [request, p, limit]
     * Return java.lang.String
     * Date 2018/5/11 9:44
     */
    @GetMapping(value = "page/{p}")
    public String index(HttpServletRequest request, @PathVariable int p, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        p = p < 0 || p > WebConstant.MAX_PAGE ? 1 : p;
        PageInfo<Contents> articles = contentService.getContentsByPageInfo(p, limit);
        request.setAttribute("articles", articles);
        if (p > 1) {
            this.title(request, "第" + p + "页");
        }
        return this.render("index");
    }

    /**
     * 文章页
     */
    @CsrfToken(newToken = true)
    @GetRoute(value = {"article/:cid", "article/:cid.html"})
    public String post(Request request, @PathParam String cid) {
        Optional<Contents> contentsOptional = contentsService.getContents(cid);
        if (!contentsOptional.isPresent()) {
            return this.render_404();
        }
        Contents contents = contentsOptional.get();
        if (Types.DRAFT.equals(contents.getStatus())) {
            return this.render_404();
        }
        request.attribute("article", contents);
        request.attribute("is_post", true);
        if (contents.getAllowComment()) {
            int cp = request.queryInt("cp", 1);
            request.attribute("cp", cp);
        }
        Contents temp = new Contents();
        temp.setHits(contents.getHits() + 1);
        temp.setCid(contents.getCid());
//        temp.update(contents.getCid());
        contentService.updateContent(temp);
        return this.render("post");
    }

    /**
     * 搜索页
     *
     * @param keyword
     * @return
     */
    @GetRoute(value = {"search/:keyword", "search/:keyword.html"})
    public String search(Request request, @PathParam String keyword, @Param(defaultValue = "12") int limit) {
        return this.search(request, keyword, 1, limit);
    }

    @GetRoute(value = {"search", "search.html"})
    public String search(Request request, @Param(defaultValue = "12") int limit) {
        String keyword = request.query("s").orElse("");
        return this.search(request, keyword, 1, limit);
    }

    @GetRoute(value = {"search/:keyword/:page", "search/:keyword/:page.html"})
    public String search(Request request, @PathParam String keyword, @PathParam int page, @Param(defaultValue = "12") int limit) {

//        page = page < 0 || page > TaleConst.MAX_PAGE ? 1 : page;
//
//        Page<Contents> articles = new Contents().where("type", Types.ARTICLE).and("status", Types.PUBLISH)
//                .like("title", "%" + keyword + "%").page(page, limit, "created desc");
//
//        request.attribute("articles", articles);
//
//        request.attribute("type", "搜索");
//        request.attribute("keyword", keyword);
//        request.attribute("page_prefix", "/search/" + keyword);
        return this.render("page-category");
    }

    /**
     * 归档页
     *
     * @return
     */
    @GetRoute(value = {"archives", "archives.html"})
    public String archives(Request request) {
        List<Archive> archives = siteService.getArchives();
        request.attribute("archives", archives);
        request.attribute("is_archive", true);
        return this.render("archives");
    }

    /**
     * feed页
     *
     * @return
     */
    @GetRoute(value = {"feed", "feed.xml", "atom.xml"})
    public void feed(Response response) {

//        List<Contents> articles = new Contents().where("type", Types.ARTICLE).and("status", Types.PUBLISH)
//                .and("allow_feed", true)
//                .findAll(OrderBy.desc("created"));
//
//        try {
//            String xml = TaleUtils.getRssXml(articles);
//            response.contentType("text/xml; charset=utf-8");
//            response.body(xml);
//        } catch (Exception e) {
//            log.error("生成 rss 失败", e);
//        }
    }

    /**
     * sitemap 站点地图
     *
     * @return
     */
    @GetRoute(value = {"sitemap", "sitemap.xml"})
    public void sitemap(Response response) {
//        List<Contents> articles = new Contents().where("type", Types.ARTICLE).and("status", Types.PUBLISH)
//                .and("allow_feed", true)
//                .findAll(OrderBy.desc("created"));
//
//        try {
//            String xml = TaleUtils.getSitemapXml(articles);
//            response.contentType("text/xml; charset=utf-8");
//            response.body(xml);
//        } catch (Exception e) {
//            log.error("生成 sitemap 失败", e);
//        }
    }

    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @Route(value = "logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        TaleUtils.logout(session, response);
    }

    /**
     * 评论操作
     */
    @CsrfToken(valid = true)
    @PostRoute(value = "comment")
    @JSON
    public RestResponse comment(Request request, Response response,
                                @HeaderParam String Referer, @Valid Comments comments) {

        if (StringKit.isBlank(Referer)) {
            return RestResponse.fail(ErrorCode.BAD_REQUEST);
        }

        if (!Referer.startsWith(Commons.site_url())) {
            return RestResponse.fail("非法评论来源");
        }

        String  val   = request.address() + ":" + comments.getCid();
        Integer count = cache.hget(Types.COMMENTS_FREQUENCY, val);
        if (null != count && count > 0) {
            return RestResponse.fail("您发表评论太快了，请过会再试");
        }

        comments.setAuthor(TaleUtils.cleanXSS(comments.getAuthor()));
        comments.setContent(TaleUtils.cleanXSS(comments.getContent()));

        comments.setAuthor(EmojiParser.parseToAliases(comments.getAuthor()));
        comments.setContent(EmojiParser.parseToAliases(comments.getContent()));
        comments.setIp(request.address());
        comments.setParent(comments.getCoid());

        try {
            commentsService.saveComment(comments);
            response.cookie("tale_remember_author", URLEncoder.encode(comments.getAuthor(), "UTF-8"), 7 * 24 * 60 * 60);
            response.cookie("tale_remember_mail", URLEncoder.encode(comments.getMail(), "UTF-8"), 7 * 24 * 60 * 60);
            if (StringKit.isNotBlank(comments.getUrl())) {
                response.cookie("tale_remember_url", URLEncoder.encode(comments.getUrl(), "UTF-8"), 7 * 24 * 60 * 60);
            }

            // 设置对每个文章30秒可以评论一次
            cache.hset(Types.COMMENTS_FREQUENCY, val, 1, 30);
            siteService.cleanCache(Types.C_STATISTICS);

            return RestResponse.ok();
        } catch (Exception e) {
            String msg = "评论发布失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
    }

}