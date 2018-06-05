package com.nmys.story.model.dto;

/**
 * Description:常量类型
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/14 17:28
 */
public interface Types {

    String TAG = "tag";
    String CATEGORY = "category";

    String LINK = "link";

    // 文章类型
    String ARTICLE = "post";

    // 发布状态
    String PUBLISH = "publish";
    String PAGE = "page";
    String DRAFT = "draft";
    String IMAGE = "image";
    String FILE = "file";
    String CSRF_TOKEN = "csrf_token";
    String COMMENTS_FREQUENCY = "comments:frequency";

    // 文章点击的频率
    String HITS_FREQUENCY = "hits:frequency";

    // 最新最近文章
    String RECENT_ARTICLE = "recent_article";

    // 随机文章
    String RANDOM_ARTICLE = "random_article";

    String RECENT_META = "recent_meta";
    String RANDOM_META = "random_meta";

    String C_STATISTICS = "sys:statistics";

    String C_ARTICLE_HITS = "article:hits";

    String NEXT = "next";
    String PREV = "prev";

    /**
     * 附件存放的URL，默认为网站地址，如集成第三方则为第三方CDN域名
     */
    String ATTACH_URL = "attach_url";

    /**
     * 网站要过滤，禁止访问的ip列表
     */
    String BLOCK_IPS = "site_block_ips";

    String VISIT_COUNT = "visit_count";

}
