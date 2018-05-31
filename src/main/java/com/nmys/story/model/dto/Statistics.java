package com.nmys.story.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 后台统计对象
 * <p>
 * Created by 70kg
 */
@Data
public class Statistics implements Serializable {

    // 文章数
    private long articles = 10;
    // 页面数
    private long pages = 10;
    // 评论数
    private long comments = 10;
    // 分类数
    private long categories = 10;
    // 标签数
    private long tags = 10;
    // 附件数
    private long attachs = 10;

}
