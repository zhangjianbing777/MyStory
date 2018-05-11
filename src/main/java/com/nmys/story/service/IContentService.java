package com.nmys.story.service;

import com.github.pagehelper.PageInfo;
import com.nmys.story.model.entity.Contents;

import java.util.List;

/**
 * Description:内容接口
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


}
