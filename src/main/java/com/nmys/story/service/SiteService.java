package com.nmys.story.service;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.StringKit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.mapper.ContentsMapper;
import com.nmys.story.model.dto.Archive;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.utils.DateKit;
import com.nmys.story.utils.MapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:站点service
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/17 10:10
 */
@Bean
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

    /**
     * Description: 查询归档
     * author: itachi
     * Date: 2018/5/12 下午8:59
     */
    public List<Archive> getArchives() {
        // 获取归档大类
        List<Archive> archives = contentsMapper.selectArchive();
        if (null != archives) {
            for (Archive archive : archives) {
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
        return archives;
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
//        Page<Comments> commentsPage = new Comments().page(1, limit, "created desc");
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
//            Page<Contents> contentsPage = new Contents().where("status", Types.PUBLISH).and("type", Types.ARTICLE)
//                    .page(1, limit, "created desc");
            return pageInfo.getList();
        }

        // 随机文章
//        if (Types.RANDOM_ARTICLE.equals(type)) {
//            List<Integer> cids = new ActiveRecord().queryAll(Integer.class, "select cid from t_contents where type = ? and status = ? order by random() * cid limit ?", Types.ARTICLE, Types.PUBLISH, limit);
//            if (BladeKit.isNotEmpty(cids)) {
//                return new Contents().in("cid", cids).findAll();
//            }
//        }
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


    /**
     * 清除缓存
     *
     * @param key 缓存key
     */
    public void cleanCache(String key) {
        if (StringKit.isNotBlank(key)) {
            if ("*".equals(key)) {
                mapCache.clean();
            } else {
                mapCache.del(key);
            }
        }
    }

}
