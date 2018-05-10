package com.nmys.story.utils;

import com.blade.kit.DateKit;
import com.blade.kit.Hashids;
import com.blade.kit.StringKit;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.admin.AttachController;
import com.nmys.story.extension.Commons;
import com.nmys.story.extension.Theme;
import com.nmys.story.init.TaleConst;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.model.entity.Users;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;
import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.nmys.story.init.TaleConst.*;

/**
 * Description: tale工具类
 * author: itachi
 * Date: 2018/5/10 下午10:36
 */
public class TaleUtils {

    private static final Logger logger = LoggerFactory.getLogger(TaleUtils.class);

    /**
     * 一个月
     */
    private static final int ONE_MONTH = 30 * 24 * 60 * 60;
    private static final Random R = new Random();
    private static final Hashids HASH_IDS = new Hashids(TaleConst.AES_SALT);
    private static final long[] HASH_PREFIX = {-1, 2, 0, 1, 7, 0, 9};

    /**
     * 匹配邮箱正则
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern SLUG_REGEX = Pattern.compile("^[A-Za-z0-9_-]{5,100}$", Pattern.CASE_INSENSITIVE);

    /**
     * 设置记住密码cookie
     *
     * @param response
     * @param uid
     */
    public static void setCookie(HttpServletResponse response, Integer uid) {
        try {
            String val = Tools.enAes(uid.toString(), WebConstant.AES_SALT);
            boolean isSSL = false;
            Cookie cookie = new Cookie(WebConstant.USER_IN_COOKIE, val);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 30);
            cookie.setSecure(isSSL);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Description:返回当前登录用户
     * Author:70kg
     * Param [request]
     * Return com.nmys.story.model.entity.Users
     * Date 2018/5/9 10:21
     */
    public static Users getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        return (Users) session.getAttribute(WebConstant.LOGIN_SESSION_KEY);
    }

    /**
     * Description:退出登录
     * Author:70kg
     * Param [session, response]
     * Return void
     * Date 2018/5/9 10:27
     */
    public static void logout(HttpSession session, HttpServletResponse response) {
        // 移除session
        session.removeAttribute(WebConstant.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConstant.USER_IN_COOKIE, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        try {
            response.sendRedirect(Commons.site_url());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取cookie中的用户id
     *
     * @param request
     * @return
     */
    public static Integer getCookieUid(HttpServletRequest request) {
        if (null != request) {
            Cookie cookie = cookieRaw(WebConstant.USER_IN_COOKIE, request);
            if (cookie != null && cookie.getValue() != null) {
                try {
                    String uid = Tools.deAes(cookie.getValue(), WebConstant.AES_SALT);
                    return StringUtils.isNotBlank(uid) && Tools.isNumber(uid) ? Integer.valueOf(uid) : null;
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * 从cookies中获取指定cookie
     *
     * @param name    名称
     * @param request 请求
     * @return cookie
     */
    private static Cookie cookieRaw(String name, HttpServletRequest request) {
        Cookie[] servletCookies = request.getCookies();
        if (servletCookies == null) {
            return null;
        }
        for (Cookie c : servletCookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * 重新拼接字符串
     *
     * @param arr
     * @return
     */
    public static String rejoin(String[] arr) {
        if (null == arr) {
            return "";
        }
        if (arr.length == 1) {
            return "'" + arr[0] + "'";
        }
        String a = String.join("','", arr);
        a = a.substring(2) + "'";
        return a;
    }

    /**
     * markdown转换为html
     *
     * @param markdown
     * @return
     */
    public static String mdToHtml(String markdown) {
        if (StringKit.isBlank(markdown)) {
            return "";
        }

        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new LinkAttributeProvider())
                .extensions(extensions).build();

        String content = renderer.render(document);
        content = Commons.emoji(content);

        // 支持网易云音乐输出
        if (TaleConst.BCONF.getBoolean(ENV_SUPPORT_163_MUSIC, true) && content.contains(MP3_PREFIX)) {
            content = content.replaceAll(MUSIC_REG_PATTERN, MUSIC_IFRAME);
        }
        // 支持gist代码输出
        if (TaleConst.BCONF.getBoolean(ENV_SUPPORT_GIST, true) && content.contains(GIST_PREFIX_URL)) {
            content = content.replaceAll(GIST_REG_PATTERN, GIST_REPLATE_PATTERN);
        }
        return content;
    }

    static class LinkAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }

    /**
     * 提取html中的文字
     *
     * @param html
     * @return
     */
    public static String htmlToText(String html) {
        if (StringKit.isNotBlank(html)) {
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }

    /**
     * 判断文件是否是图片类型
     *
     * @param imageFile
     * @return
     */
    public static boolean isImage(File imageFile) {
        if (!imageFile.exists()) {
            return false;
        }
        try {
            Image img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否是邮箱
     *
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * 判断是否是合法路径
     *
     * @param slug
     * @return
     */
    public static boolean isPath(String slug) {
        if (StringKit.isNotBlank(slug)) {
            if (slug.contains("/") || slug.contains(" ") || slug.contains(".")) {
                return false;
            }
            Matcher matcher = SLUG_REGEX.matcher(slug);
            return matcher.find();
        }
        return false;
    }

    /**
     * 获取RSS输出
     *
     * @param articles
     * @return
     * @throws FeedException
     */
    public static String getRssXml(java.util.List<Contents> articles) throws FeedException {
        Channel channel = new Channel("rss_2.0");
        channel.setTitle(TaleConst.OPTIONS.get("site_title", ""));
        channel.setLink(Commons.site_url());
        channel.setDescription(TaleConst.OPTIONS.get("site_description", ""));
        channel.setLanguage("zh-CN");
        java.util.List<Item> items = new ArrayList<>();
        articles.forEach(post -> {
            Item item = new Item();
            item.setTitle(post.getTitle());
            Content content = new Content();
            String value = Theme.article(post.getContent());

            char[] xmlChar = value.toCharArray();
            for (int i = 0; i < xmlChar.length; ++i) {
                if (xmlChar[i] > 0xFFFD) {
                    //直接替换掉0xb
                    xmlChar[i] = ' ';
                } else if (xmlChar[i] < 0x20 && xmlChar[i] != 't' & xmlChar[i] != 'n' & xmlChar[i] != 'r') {
                    //直接替换掉0xb
                    xmlChar[i] = ' ';
                }
            }

            value = new String(xmlChar);

            content.setValue(value);
            item.setContent(content);
            item.setLink(Theme.permalink(post.getCid(), post.getSlug()));
            item.setPubDate(DateKit.toDate(post.getCreated()));
            items.add(item);
        });
        channel.setItems(items);
        WireFeedOutput out = new WireFeedOutput();
        return out.outputString(channel);
    }

    private static final String SITEMAP_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";

    static class Url {
        String loc;
        String lastmod;

        public Url(String loc) {
            this.loc = loc;
        }
    }

    public static String getSitemapXml(List<Contents> articles) {
        List<Url> urls = articles.stream()
                .map(TaleUtils::parse)
                .collect(Collectors.toList());
        urls.add(new Url(Commons.site_url() + "/archives"));

        String urlBody = urls.stream()
                .map(url -> {
                    String s = "<url><loc>" + url.loc + "</loc>";
                    if (null != url.lastmod) {
                        s += "<lastmod>" + url.lastmod + "</lastmod>";
                    }
                    return s + "</url>";
                })
                .collect(Collectors.joining("\n"));

        return SITEMAP_HEAD + urlBody + "</urlset>";
    }

    private static Url parse(Contents contents) {
        Url url = new Url(Commons.site_url() + "/article/" + contents.getCid());
        url.lastmod = DateKit.toString(contents.getModified(), "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return url;
    }

    /**
     * 替换HTML脚本
     *
     * @param value
     * @return
     */
    public static String cleanXSS(String value) {
        //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    /**
     * 获取某个范围内的随机数
     *
     * @param max 最大值
     * @param len 取多少个
     * @return
     */
    public static int[] random(int max, int len) {
        int values[] = new int[max];
        int temp1, temp2, temp3;
        for (int i = 0; i < values.length; i++) {
            values[i] = i + 1;
        }
        //随机交换values.length次
        for (int i = 0; i < values.length; i++) {
            temp1 = Math.abs(R.nextInt()) % (values.length - 1); //随机产生一个位置
            temp2 = Math.abs(R.nextInt()) % (values.length - 1); //随机产生另一个位置
            if (temp1 != temp2) {
                temp3 = values[temp1];
                values[temp1] = values[temp2];
                values[temp2] = temp3;
            }
        }
        return Arrays.copyOf(values, len);
    }

    /**
     * 将list转为 (1, 2, 4) 这样的sql输出
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String listToInSql(java.util.List<T> list) {
        StringBuffer sbuf = new StringBuffer();
        list.forEach(item -> sbuf.append(',').append(item.toString()));
        sbuf.append(')');
        return '(' + sbuf.substring(1);
    }

    public static final String UP_DIR = AttachController.CLASSPATH.substring(0, AttachController.CLASSPATH.length() - 1);

    public static String getFileKey(String name) {
        String prefix = "/upload/" + DateKit.toString(new Date(), "yyyy/MM");
        String dir = UP_DIR + prefix;
        if (!Files.exists(Paths.get(dir))) {
            new File(dir).mkdirs();
        }
        return prefix + "/" + com.blade.kit.UUID.UU32() + "." + StringKit.fileExt(name);
    }


    /**
     * Description: MD5加密
     * author: itachi
     * Date: 2018/5/8 下午10:55
     */
    public static String MD5encode(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
        }
        byte[] encode = messageDigest.digest(source.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte anEncode : encode) {
            String hex = Integer.toHexString(0xff & anEncode);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
