package com.nmys.story.mapper;

import com.nmys.story.model.entity.Comments;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentMapper {

    /**
     * Description:评论总数
     * Author:70kg
     * Param []
     * Return int
     * Date 2018/5/9 13:35
     */
    int selectCommentCount();

    /**
     * Description:根据id来查询评论内容
     * Author:70kg
     * Param [id]
     * Return com.nmys.story.model.entity.Comments
     * Date 2018/5/9 13:35
     */
    Comments selectCommentByid(@Param("id") Integer id);

    /**
     * Description:查询最新评论
     * Author:70kg
     * Param []
     * Return java.util.List<com.nmys.story.model.entity.Comments>
     * Date 2018/5/9 14:56
     */
    List<Comments> selectNewComments();

    /**
     * Description:新增评论
     * Author:70kg
     * Param [comment]
     * Return int
     * Date 2018/5/9 15:03
     */
    int saveComment(Comments comment);

    /**
     * Description:根据id删除评论
     * Author:70kg
     * Param [id]
     * Return boolean
     * Date 2018/5/9 15:13
     */
    boolean delCommentById(@Param("id") Integer id);

    /**
     * Description:查询非己评论
     * Author:70kg
     * Param [authorId]
     * Return java.util.List<com.nmys.story.model.entity.Comments>
     * Date 2018/5/10 14:14
     */
    List<Comments> selectCommentsByAuthorId(@Param("authorId") Integer authorId);

}
