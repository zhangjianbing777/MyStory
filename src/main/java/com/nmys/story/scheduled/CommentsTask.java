package com.nmys.story.scheduled;

import com.nmys.story.constant.WebConstant;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.service.ICommentService;
import com.nmys.story.utils.IPKit;
import org.apache.commons.lang3.StringUtils;
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

    // 每天上午9点,下午1点,晚上8点触发
    @Scheduled(cron = "0 0 9,13,20 * * ?")
    public void autoCheckCommentsTrigger() {
        List<Comments> notAuditComments = commentService.getNotAuditComments();
        if (notAuditComments.size() != 0) {
            notAuditComments.forEach(comment -> {
                commentService.updateCommentById(comment.getCid());
                logger.info("----------自动审核评论成功,评论id:" + comment.getCid() + "----------");
            });
        } else {
            logger.info("----------没找到未审核的评论----------");
        }
    }

    // 每天上午8点,中午12点,晚上7点触发
    @Scheduled(cron = "0 0 8,12,19 * * ?")
    public void getAuthorPosition() {
        List<Comments> notAuditComments = commentService.getNotAuditComments();
        if (notAuditComments.size() != 0) {
            notAuditComments.forEach(comment -> {
                String authorPosition = null;
                try {
                    String author = comment.getAuthor();
                    if (StringUtils.isNotBlank(author) && WebConstant.DEFAULT_AUTHOR.equals(author)) {
                        // 调用淘宝免费接口获取ip信息
                        authorPosition = IPKit.getIpInformationFromTaoBao(comment.getIp());
                        comment.setAuthor(authorPosition + "网友");
                        comment.setAgent(authorPosition);
                        commentService.updateComment(comment);
                    }
                } catch (Exception e) {
                    logger.error("调用淘宝免费接口获取ip信息超时或更新ip信息失败" + e.getMessage());
                }
                logger.info("----------自动获取ip信息成功，ip信息：" + comment.getIp() + "----------");
            });
        } else {
            logger.info("----------没找到需要获取信息的ip----------");
        }
    }

}
