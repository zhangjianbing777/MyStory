package com.nmys.story.service;

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

}
