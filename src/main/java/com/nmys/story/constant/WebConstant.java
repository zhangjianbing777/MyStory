package com.nmys.story.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author itachi
 * @Title: WebConstant
 * @Description: 常量存储
 * @date 2018/5/8下午10:37
 */
public class WebConstant {

    public static Map<String, String> initConfig = new HashMap<>();

    public static String LOGIN_SESSION_KEY = "login_user";

    public static final String USER_IN_COOKIE = "S_L_ID";

    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";

    /**
     * 最大获取文章条数
     */
    public static final int MAX_POSTS = 9999;

    /**
     * 最大页码
     */
    public static final int MAX_PAGE = 100;

    /**
     * 文章最多可以输入的文字数
     */
    public static final int MAX_TEXT_COUNT = 200000;

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;

    /**
     * 点击次数超过多少更新到数据库
     */
    public static final int HIT_EXCEED = 10;

    /**
     * 上传文件最大1M
     */
    public static Integer MAX_FILE_SIZE = 1048576;

    /**
     * 成功返回
     */
    public static String SUCCESS_RESULT = "SUCCESS";

    /**
     * 同一篇文章在15分钟内无论点击多少次只算一次阅读
     */
    public static Integer HITS_LIMIT_TIME = 900;

    /**
     * 访问次数记录，30分钟
     */
    public static Integer VISIT_COUNT_TIME = 1800;

    public static String VISITOR = "访客访问";

    public static String DEFAULT_AUTHOR = "热心网友";

    /**
     * 管理员昵称
     */
    public static String ADMIN_NAME = "70KG";

    /**
     * 后台会员状态正常
     */
    public static String STATUS_1 = "1";

    /**
     * 后台会员状态锁定
     */
    public static String STATUS_0 = "0";

    /**
     * 后台普通会员标识
     */
    public static String USER_ROLE = "user";

    /**
     * 后台管理员标识
     */
    public static String USER_ADMIN = "admin";

    /**
     * 前台文章归档页面数据有效期(天)
     */
    public static Integer ARCHIVE_EXPIRETIME = 1;

    public static String DRAFTENGLISG = "draft";

    public static String DRAFTCHINESE = "草稿";

    public static String USER_ACTION_1 = "上传图片";

    public static String USER_ACTION_2 = "删除图片";

    /**
     * 访客码
     */
    public static Integer USER_VISITOR = 0;

    /**
     * 评论审核通过
     */
    public static String APPROVED = "approved";

    /**
     * 评论审核失败
     */
    public static String NOT_AUDIT = "not_audit";

}
