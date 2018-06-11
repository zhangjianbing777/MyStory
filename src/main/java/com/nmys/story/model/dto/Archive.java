package com.nmys.story.model.dto;
import com.nmys.story.model.entity.Contents;
import java.util.List;

/**
 * Description: 文章归档
 * author: itachi
 * Date: 2018/5/12 下午8:49
 */
public class Archive {

    private String date_str;

    // 文章日期
    private String date;

    // 文章数量
    private String count;

    // 文章集合
    private List<Contents> articles;

    public String getDate_str() {
        return date_str;
    }

    public void setDate_str(String date_str) {
        this.date_str = date_str;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Contents> getArticles() {
        return articles;
    }

    public void setArticles(List<Contents> articles) {
        this.articles = articles;
    }
}
