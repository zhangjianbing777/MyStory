package com.nmys.story.service;

import com.github.pagehelper.PageInfo;
import com.nmys.story.model.entity.Contents;

import java.util.List;

/**
 * Description:文章内容接口
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/9 11:17
 */
public interface IContentService {

    /**
     * Description:根据类型获取文章列表
     * Author:70kg
     * Param [type]
     * Return java.util.List<com.nmys.story.model.entity.Contents>
     * Date 2018/5/10 9:48
     */
    List<Contents> getContentsByType(String type, String status);

    /**
     * Description:博客前台文章分页查询
     * Author:70kg
     * Param [page, limit]
     * Return java.util.List<com.nmys.story.model.entity.Contents>
     * Date 2018/5/11 9:32
     */
    PageInfo<Contents> getContentsByPageInfo(Integer page, Integer limit);

    /**
     * Description:更新文章内容
     * Author:70kg
     * Param [content]
     * Description:根据id来获取文章详情
     * Return boolean
     * Date 2018/5/11 14:01
     */
    boolean updateContent(Contents content);

    /**
     * Author:70kg
     * Param [id]
     * Return com.nmys.story.model.entity.Contents
     * Date 2018/5/11 15:55
     */
    Contents getContentById(Integer id);

    /**
     * Description: 根据文章缩略名来查询文章
     * author: itachi
     * Date: 2018/5/13 上午10:25
     */
    Contents getContentBySlug(String slug);

    /**
     * Description: 根据特定条件来查询文章
     * author: itachi
     * Date: 2018/5/13 下午2:24
     */
    PageInfo<Contents> getContentsConditions(String type, Integer page, Integer limit);

    /**
     * Description: 保存文章
     * author: itachi
     * Date: 2018/5/13 下午3:22
     */
    String saveContent(Contents content);

    /**
     * Description:
     * Author:70kg
     * Param [page, limit]
     * Return com.github.pagehelper.PageInfo<com.nmys.story.model.entity.Contents>
     * Date 2018/5/14 14:39
     */
    PageInfo<Contents> getArticlesWithpage(Integer page, Integer limit);


    /**
     * Description:根据id删除文章
     * Author:70kg
     * Param [id]
     * Return java.lang.String
     * Date 2018/5/14 14:52
     */
    String delContentById(Integer id);


    /**
     * Description:查询标签下面的所属文章
     * Author:70kg
     * Param [mid, page, limit] meta的id
     * Return com.github.pagehelper.PageInfo<com.nmys.story.model.entity.Contents>
     * Date 2018/5/16 18:16
     */
    PageInfo<Contents> getTagArticles(Integer mid, int page, int limit);

    /**
     * Description: 根据title来搜索文章
     * Author:70kg
     * Param [title, page, limit]
     * Return com.github.pagehelper.PageInfo<com.nmys.story.model.entity.Contents>
     * Date 2018/6/4 17:11
     */
    PageInfo<Contents> searchContentByTitle(String title, int page, int limit);

    /**
     * Description: 修改文章标签关系表
     * Author:70KG
     * Param [cid, tags, categories] 本篇文章的id，文章的标签字符串数组，文章的分类
     * Date 2018/9/20 17:53
     */
    void updateRelationsShips(Integer cid, String tags, String categories);

    PageInfo<Contents> getArticlesByConditions(String article, String tag, String status, int page, int limit);
}
