package com.nmys.story.mapper;

import com.nmys.story.model.entity.Contents;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContentsMapper {

    /**
     * Description:根据类型查询最新发布的文章
     * Author:70kg
     * Param [Type]
     * Return java.util.List<com.nmys.story.model.entity.Contents>
     * Date 2018/5/10 9:50
     */
    List<Contents> getContentsByType(@Param("type") String type, @Param("status") String status);

    /**
     * Description:更新文章
     * Author:70kg
     * Param [content]
     * Return boolean
     * Date 2018/5/11 14:02
     */
    boolean updateContent(Contents content);

    /**
     * Description:根据id获取文章
     * Author:70kg
     * Param [id]
     * Return com.nmys.story.model.entity.Contents
     * Date 2018/5/11 15:56
     */
    Contents getContentById(@Param("id") Integer id);

}
