package com.nmys.story.model.dto;

import com.nmys.story.model.entity.Comments;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by 70kg
 */
@Data
@ToString(callSuper = true)
public class Comment extends Comments {

    private int levels;
    private List<Comments> children;

    public Comment(Comments comments) {
        setAuthor(comments.getAuthor());
        setMail(comments.getMail());
        setCoid(comments.getCoid());
        setAuthor_id(comments.getAuthor_id());
        setUrl(comments.getUrl());
        setCreated(comments.getCreated());
        setAgent(comments.getAgent());
        setIp(comments.getIp());
        setContent(comments.getContent());
        setOwner_id(comments.getOwner_id());
        setCid(comments.getCid());
    }

}
