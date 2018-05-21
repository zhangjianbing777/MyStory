package com.nmys.story.scheduled;

import com.nmys.story.model.entity.Comments;
import com.nmys.story.service.ICommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:评论自动审核
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/16 13:46
 */
@Component
public class CommentsTask {

    private static final Logger logger = LoggerFactory.getLogger(CommentsTask.class);

    @Autowired
    private ICommentService commentService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void autoCheckCommentsTrigger() {
        List<Comments> notAuditComments = commentService.getNotAuditComments();
        if (notAuditComments.size() != 0) {
            for (Comments comment : notAuditComments) {
                commentService.updateCommentById(comment.getCid());
                logger.info("----------自动审核评论成功,评论id:" + comment.getCid() + "----------");
            }
        } else {
            logger.info("----------没找到未审核的评论----------");
        }
    }

}
