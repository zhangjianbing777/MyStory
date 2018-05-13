package com.nmys.story.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.OrderBy;
import com.blade.jdbc.page.Page;
import com.blade.kit.PatternKit;
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
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.Archive;
import com.nmys.story.model.dto.ErrorCode;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.service.*;
import com.nmys.story.utils.IPKit;
import com.nmys.story.utils.TaleUtils;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Inject
    private ContentsService contentsService;

    @Inject
    private MetasService metasService;

    @Inject
    private CommentsService commentsService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private IContentService contentService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IMetaService metaService;

    /**
     * Description:博客首页
     * Author:70kg
     * Param [request, limit]
     * Return java.lang.String
     * Date 2018/5/11 9:22
     */
    @GetMapping(value = "/")
    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "6") int limit) {
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
        if (contents.getAllowComment() == 1) {
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
    public String index(HttpServletRequest request, @PathVariable int p, @RequestParam(value = "limit", defaultValue = "6") int limit) {
        p = p < 0 || p > WebConstant.MAX_PAGE ? 1 : p;
        PageInfo<Contents> articles = contentService.getContentsByPageInfo(p, limit);
        request.setAttribute("articles", articles);
        if (p > 1) {
            this.title(request, "第" + p + "页");
        }
        return this.render("index");
    }


    /**
     * Description:文章详情页
     * Author:70kg
     * Param [request, cid]
     * Return java.lang.String
     * Date 2018/5/11 15:54
     */
    @GetMapping(value = {"article/{cid}", "article/{cid}.html"})
    public String getArticle(HttpServletRequest request, @PathVariable String cid) {
        Contents contents = contentService.getContentById(Integer.parseInt(cid));
        if (null == contents || "draft".equals(contents.getStatus())) {
            return this.render_404();
        }
        request.setAttribute("article", contents);
        request.setAttribute("is_post", true);
        // 评论
        completeArticle(request, contents);
        // 点击量
        updateArticleHit(contents.getCid(), contents.getHits());
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
     * Description: 归档页面
     * author: itachi
     * Date: 2018/5/12 下午8:29
     */
    @GetMapping(value = "archives")
    public String archives(HttpServletRequest request) {
        List<Archive> list = siteService.getArchives();
        request.setAttribute("archives", list);
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
        List<Contents> articles = new Contents().where("type", Types.ARTICLE).and("status", Types.PUBLISH)
                .and("allow_feed", true)
                .findAll(OrderBy.desc("created"));

        try {
            String xml = null;//TaleUtils.getSitemapXml(articles);
            response.contentType("text/xml; charset=utf-8");
            response.body(xml);
        } catch (Exception e) {
            log.error("生成 sitemap 失败", e);
        }
    }

    /**
     * Description: 友链
     * author: itachi
     * Date: 2018/5/12 下午7:57
     */
    @GetMapping(value = "links")
    public String links(HttpServletRequest request) {
        List<Metas> links = metaService.getMetasByType(Types.LINK);
        request.setAttribute("links", links);
        return this.render("links");
    }


    /**
     * Description: 前台注销操作
     * author: itachi
     * Date: 2018/5/12 下午6:36
     */
    @Route(value = "logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        TaleUtils.logout(session, response);
    }


    /**
     * Description: 前台评论操作
     * author: itachi
     * Date: 2018/5/12 下午6:37
     */
    @PostMapping(value = "comment")
    @ResponseBody
    public RestResponseBo comment(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam Integer cid, @RequestParam Integer coid,
                                  @RequestParam String author, @RequestParam String mail,
                                  @RequestParam String url, @RequestParam String text, @RequestParam String _csrf_token) {

        String ref = request.getHeader("Referer");
        if (StringUtils.isBlank(ref) || StringUtils.isBlank(_csrf_token)) {
            return RestResponseBo.fail(ErrorCode.BAD_REQUEST);
        }

        // ????????
//        String token = cache.hget(Types.CSRF_TOKEN, _csrf_token);
//        if (StringUtils.isBlank(token)) {
//            return RestResponseBo.fail(ErrorCode.BAD_REQUEST);
//        }

        if (null == cid || StringUtils.isBlank(text)) {
            return RestResponseBo.fail("请输入完整后评论");
        }

        if (StringUtils.isNotBlank(author) && author.length() > 50) {
            return RestResponseBo.fail("姓名过长");
        }

        if (StringUtils.isNotBlank(mail) && !TaleUtils.isEmail(mail)) {
            return RestResponseBo.fail("请输入正确的邮箱格式");
        }

        if (StringUtils.isNotBlank(url) && !PatternKit.isURL(url)) {
            return RestResponseBo.fail("请输入正确的URL格式");
        }

        if (text.length() > 200) {
            return RestResponseBo.fail("请输入200个字符以内的评论");
        }

        String val = IPKit.getIpAddrByRequest(request) + ":" + cid;
        Integer count = cache.hget(Types.COMMENTS_FREQUENCY, val);
        if (null != count && count > 0) {
            return RestResponseBo.fail("您发表评论太快了，请过会再试");
        }

        author = TaleUtils.cleanXSS(author);
        text = TaleUtils.cleanXSS(text);

        author = EmojiParser.parseToAliases(author);
        text = EmojiParser.parseToAliases(text);

        Comments comments = new Comments();
        comments.setAuthor(author);
        comments.setCid(cid);
        comments.setIp(request.getRemoteAddr());
        comments.setUrl(url);
        comments.setContent(text);
        comments.setMail(mail);
        comments.setParent(coid);
        try {
            String result = commentService.insertComment(comments);
            cookie("tale_remember_author", URLEncoder.encode(author, "UTF-8"), 7 * 24 * 60 * 60, response);
            cookie("tale_remember_mail", URLEncoder.encode(mail, "UTF-8"), 7 * 24 * 60 * 60, response);
            if (StringUtils.isNotBlank(url)) {
                cookie("tale_remember_url", URLEncoder.encode(url, "UTF-8"), 7 * 24 * 60 * 60, response);
            }
            // 设置对每个文章1分钟可以评论一次
            cache.hset(Types.COMMENTS_FREQUENCY, val, 1, 60);
            if (!WebConstant.SUCCESS_RESULT.equals(result)) {
                return RestResponseBo.fail(result);
            }
            return RestResponseBo.ok();
        } catch (Exception e) {
            String msg = "评论发布失败";
            logger.error(msg, e);
            return RestResponseBo.fail(msg);
        }
    }

    /**
     * Description:设置cookie
     * Author:70kg
     * Param [name, value, maxAge, response]
     * Return void
     * Date 2018/5/11 17:08
     */
    private void cookie(String name, String value, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(false);
        response.addCookie(cookie);
    }

    /**
     * Description: 公共方法
     * author: itachi
     * Date: 2018/5/12 下午8:26
     */
    private void completeArticle(HttpServletRequest request, Contents contents) {
        // 允许评论
        if (contents.getAllowComment() == 1) {
            String cp = request.getParameter("cp");
            if (StringUtils.isBlank(cp)) {
                cp = "1";
            }
            request.setAttribute("cp", cp);
            // 每页6条评论
            PageInfo<Comments> commentsPaginator = commentService.getCommentsListByContentId(contents.getCid(), Integer.parseInt(cp), 6);
            request.setAttribute("comments", commentsPaginator);
        }
    }


    /**
     * Description:更新文章点击量
     * Author:70kg
     * Param [cid, chits]
     * Return void
     * Date 2018/5/11 17:40
     */
    private void updateArticleHit(Integer cid, Integer chits) {
        Integer hits = cache.hget("article", "hits");
        if (chits == null) {
            chits = 0;
        }
        hits = null == hits ? 1 : hits + 1;
        if (hits >= WebConstant.HIT_EXCEED) {
            Contents temp = new Contents();
            temp.setCid(cid);
            temp.setHits(chits + hits);
            contentService.updateContent(temp);
            cache.hset("article", "hits", 1);
        } else {
            cache.hset("article", "hits", hits);
        }
    }

}