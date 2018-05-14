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
     * Return boolean
     * Date 2018/5/11 14:01
     */
    boolean updateContent(Contents content);

    /**
     * Description:根据id来获取文章详情
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


}
