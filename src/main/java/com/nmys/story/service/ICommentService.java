package com.nmys.story.service;


import com.github.pagehelper.PageInfo;
import com.nmys.story.model.entity.Comments;

import java.util.List;

/**
 * Description:评论接口
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/9 11:16
 */
public interface ICommentService {

    /**
     * Description:查询评论的数量
     * Author:70kg
     * Param []
     * Return int
     * Date 2018/5/9 11:48
     */
    int selectCommentCount();

    /**
     * Description:根据id来查询评论
     * Author:70kg
     * Param [id]
     * Return com.nmys.story.model.entity.Comments
     * Date 2018/5/9 11:48
     */
    Comments selectCommentByid(Integer id);

    /**
     * Description:获取最新评论
     * Author:70kg
     * Param [page, limit]
     * Return com.github.pagehelper.PageInfo<com.nmys.story.model.entity.Comments>
     * Date 2018/5/9 15:10
     */
    PageInfo<Comments> selectNewComments(Integer page, Integer limit);

//    List<Comments> selectNewComments(Integer page, Integer limit);

    /**
     * Description:保存评论
     * Author:70kg
     * Param [comment]
     * Return int
     * Date 2018/5/9 15:10
     */
    int saveComments(Comments comment);

    /**
     * Description:根据id删除评论
     * Author:70kg
     * Param [id]
     * Return boolean
     * Date 2018/5/9 15:12
     */
    boolean delCommentById(Integer id);

    /**
     * Description:查询非自己的评论
     * Author:70kg
     * Param [authorId]
     * Return java.util.List<com.nmys.story.model.entity.Comments>
     * Date 2018/5/10 14:10
     */
    List<Comments> selectCommentsByAuthorId(Integer authorId);

}
