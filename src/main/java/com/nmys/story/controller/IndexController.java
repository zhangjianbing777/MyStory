package com.nmys.story.controller;

import com.blade.kit.PatternKit;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.Response;
import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.Archive;
import com.nmys.story.model.dto.ErrorCode;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.service.ICommentService;
import com.nmys.story.service.IContentService;
import com.nmys.story.service.IMetaService;
import com.nmys.story.service.SiteService;
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
        // 15分钟内重复点击不会更新文章浏览量
        if (!checkHitsFrequency(request, String.valueOf(contents.getCid()))) {
            // 更新文章点击量
            updateArticleHit(contents.getCid(), contents.getHits());
        }
        return this.render("post");


    }


    /**
     * Description:标签页
     * Author:70kg
     * Param [request, name, limit]
     * Return java.lang.String
     * Date 2018/5/16 18:38
     */
    @GetMapping(value = "tag/{name}")
    public String tags(HttpServletRequest request,
                       @PathVariable String name,
                       @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.tags(request, name, 1, limit);
    }

    /**
     * Description:标签的前台分页
     * Author:70kg
     * Param [request, name, page, limit]
     * Return java.lang.String
     * Date 2018/5/16 18:38
     */
    @GetMapping(value = "tag/{name}/{page}")
    public String tags(HttpServletRequest request,
                       @PathVariable String name,
                       @PathVariable int page,
                       @RequestParam(value = "limit", defaultValue = "12") int limit) {

        page = page < 0 || page > WebConstant.MAX_PAGE ? 1 : page;
        // 对于空格的特殊处理
        name = name.replaceAll("\\+", " ");
        Metas metaDto = metaService.getMeta(Types.TAG, name);
        if (null == metaDto) {
            return this.render_404();
        }

        PageInfo<Contents> contentsPaginator = contentService.getTagArticles(metaDto.getMid(), page, limit);
        request.setAttribute("articles", contentsPaginator);
        request.setAttribute("meta", metaDto);
        request.setAttribute("type", "标签");
        request.setAttribute("keyword", name);

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
     * Description: 自定义页面,如：前台关于页面
     * author: itachi
     * Date: 2018/5/13 上午10:04
     */
    @GetMapping(value = "/{pagename}")
    public String page(@PathVariable String pagename, HttpServletRequest request) {
        // 根据文章缩略名来查询
        Contents contents = contentService.getContentBySlug(pagename);
        if (null == contents) {
            return this.render_404();
        }
        // 该文章是否允许评论
        if (contents.getAllowComment() == 1) {
            String cp = request.getParameter("cp");
            if (StringUtils.isBlank(cp)) {
                cp = "1";
            }
            // 评论集合
            PageInfo<Comments> commentsPaginator = commentService.getCommentsListByContentId(contents.getCid(), Integer.parseInt(cp), 6);
            request.setAttribute("comments", commentsPaginator);
        }
        request.setAttribute("article", contents);
        if (!checkHitsFrequency(request, String.valueOf(contents.getCid()))) {
            // 更新文章点击量
            updateArticleHit(contents.getCid(), contents.getHits());
        }
        return this.render("page");
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
    public RestResponseBo comment(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam Integer cid,
                                  @RequestParam Integer coid,
                                  @RequestParam String author,
                                  @RequestParam String mail,
                                  @RequestParam String url,
                                  @RequestParam String text,
                                  @RequestParam String _csrf_token) {

        String ref = request.getHeader("Referer");
        if (StringUtils.isBlank(ref) || StringUtils.isBlank(_csrf_token)) {
            return RestResponseBo.fail(ErrorCode.BAD_REQUEST);
        }

        // ?!?!?!?!?!?!?!
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

        // 评论人的姓名
        author = EmojiParser.parseToAliases(author);
        // 评论的内容
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
            // 此处增加cookie是为了不让用户再次输入评论头部
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
        if (chits == 0 || chits == null) {
            chits = 0;
        }
        chits = chits + 1;
        Contents temp = new Contents();
        temp.setCid(cid);
        temp.setHits(chits);
        contentService.updateContent(temp);
    }

    /**
     * Description: 检查同一个ip在15分钟之内是否访问同一篇文章
     * author: itachi
     * Date: 2018/5/13 上午10:14
     */
    private boolean checkHitsFrequency(HttpServletRequest request, String cid) {
        String val = IPKit.getIpAddrByRequest(request) + ":" + cid;
        Integer count = cache.hget(Types.HITS_FREQUENCY, val);
        if (null != count && count > 0) {
            return true;
        }
        cache.hset(Types.HITS_FREQUENCY, val, 1, WebConstant.HITS_LIMIT_TIME);
        return false;
    }

    /**
     * Description:前台分类页(如:默认分类等)
     * Author:70kg
     * Param [request, keyword, limit]
     * Return java.lang.String
     * Date 2018/6/1 14:35
     */
    @GetMapping(value = "category/{keyword}")
    public String categories(HttpServletRequest request,
                             @PathVariable String keyword,
                             @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.categories(request, keyword, 1, limit);
    }

    @GetMapping(value = "category/{keyword}/{page}")
    public String categories(HttpServletRequest request,
                             @PathVariable String keyword,
                             @PathVariable int page,
                             @RequestParam(value = "limit", defaultValue = "12") int limit) {
        page = page < 0 || page > WebConstant.MAX_PAGE ? 1 : page;
        Metas metaDto = metaService.getMeta(Types.CATEGORY, keyword);
        if (null == metaDto) {
            return this.render_404();
        }

        PageInfo<Contents> contentsPaginator = contentService.getTagArticles(metaDto.getMid(), page, limit);

        request.setAttribute("articles", contentsPaginator);
        request.setAttribute("meta", metaDto);
        request.setAttribute("type", "分类");
        request.setAttribute("keyword", keyword);

        return this.render("page-category");
    }

}