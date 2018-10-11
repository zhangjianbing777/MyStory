package com.nmys.story.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.mapper.ContentsMapper;
import com.nmys.story.model.dto.Archive;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.utils.DateKit;
import com.nmys.story.utils.MapCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description:站点service
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/17 10:10
 */
@Service
public class SiteService {

    public MapCache mapCache = new MapCache();

    @Autowired
    private IUserService userService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IContentService contentService;

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * Description: 查询归档
     * author: itachi
     * Date: 2018/5/12 下午8:59
     */
    public List<Archive> getArchives() {
        // 首先从redis中获取
        String redisArchives = (String) redisTemplate.opsForValue().get("archives");
        if (StringUtils.isNotBlank(redisArchives)) {
            return JSONObject.parseArray(redisArchives, Archive.class);
        }
        // 从数据库中获取归档大类
        List<Archive> databaseArchives = contentsMapper.selectArchive();
        if (null != databaseArchives) {
            for (Archive archive : databaseArchives) {
                String date = archive.getDate();
                Date sd = DateKit.dateFormat(date, "yyyy年MM月");
                // 开始时间结束时间
                int start = DateKit.getUnixTimeByDate(sd);
                int end = DateKit.getUnixTimeByDate(DateKit.dateAdd(DateKit.INTERVAL_MONTH, sd, 1)) - 1;
                // 查询符合条件的文章
                List<Contents> contentList = contentsMapper.getContentsByConditions(Types.ARTICLE, Types.PUBLISH, start, end);
                archive.setArticles(contentList);
            }
        }
        String jsonStr = JSON.toJSONString(databaseArchives);
        // 有效期暂定一天
        redisTemplate.opsForValue().set("archives", jsonStr, WebConstant.ARCHIVE_EXPIRETIME, TimeUnit.DAYS);
        return databaseArchives;
    }


    /**
     * 最新收到的评论
     *
     * @param limit 评论数
     */
    public List<Comments> recentComments(int limit) {
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        // 查询最新10条评论
        PageInfo<Comments> commentsPageInfo = commentService.selectNewComments(1, limit);
        return commentsPageInfo.getList();
    }

    /**
     * 根据类型获取文章列表
     *
     * @param type  最新,随机
     * @param limit 获取条数
     */
    public List<Contents> getContens(String type, int limit) {
        if (limit < 0 || limit > 20) {
            limit = 10;
        }
        // 最新最近文章
        if (Types.RECENT_ARTICLE.equals(type)) {
            PageHelper.startPage(1, limit);
            // 文章类型和发布状态
            List<Contents> contentList = contentService.getContentsByType(Types.ARTICLE, Types.PUBLISH);
            PageInfo<Contents> pageInfo = new PageInfo(contentList);
            return pageInfo.getList();
        }
        return new ArrayList();
    }


    /**
     * Description:查询一条评论
     * Author:70kg
     * Param [coid]
     * Return com.nmys.story.model.entity.Comments
     * Date 2018/5/9 15:01
     */
    public Comments getComment(Integer coid) {
        if (null != coid) {
            Comments comments = commentService.selectCommentByid(coid);
            return comments;
        }
        return null;
    }

}
