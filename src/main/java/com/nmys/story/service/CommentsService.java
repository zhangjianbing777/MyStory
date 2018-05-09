package com.nmys.story.service;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.OrderBy;
import com.blade.jdbc.page.Page;
import com.blade.kit.BladeKit;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.nmys.story.exception.TipException;
import com.nmys.story.model.dto.Comment;
import com.nmys.story.model.entity.Comments;
import com.nmys.story.model.entity.Contents;
import com.nmys.story.utils.TaleUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 评论Service
 *
 * @author biezhi
 * @since 1.3.1
 */
@Bean
public class CommentsService {

    @Inject
    private ContentsService contentsService;

    @Autowired
    private ICommentService commentService;

    /**
     * 保存评论
     *
     * @param comments
     */
    public void saveComment(Comments comments) {
        if (comments.getContent().length() < 5 || comments.getContent().length() > 2000) {
            throw new TipException("评论字数在5-2000个字符");
        }
        if (null == comments.getCid()) {
            throw new TipException("评论文章不能为空");
        }
        Contents contents = new Contents().where("cid", comments.getCid()).find();
        if (null == contents) {
            throw new TipException("不存在的文章");
        }
        try {
            comments.setOwner_id(contents.getAuthorId());
            comments.setCreated(DateKit.nowUnix());
            comments.setParent(comments.getCoid());
            comments.setCoid(null);
            commentService.saveComments(comments);
//            comments.save();

            Contents temp = new Contents();
            temp.setCommentsNum(contents.getCommentsNum() + 1);
            temp.update(contents.getCid());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 删除评论，暂时没用
     *
     * @param coid
     * @param cid
     * @throws Exception
     */
    public void delete(Integer coid, Integer cid) {
        // 根据id删除评论
        boolean flag = commentService.delCommentById(coid);
//        new Comments().delete(coid);
        Contents contents = new Contents().find(cid);
        if (null != contents && contents.getCommentsNum() > 0) {
            Contents temp = new Contents();
            temp.setCommentsNum(contents.getCommentsNum() - 1);
            temp.update(cid);
        }
    }

    /**
     * 获取文章下的评论
     *
     * @param cid
     * @param page
     * @param limit
     * @return
     */
    public Page<Comment> getComments(Integer cid, int page, int limit) {
//        if (null != cid) {
//            Page<Comments> cp = new Comments().where("cid", cid).and("parent", 0).page(page, limit, "coid desc");
//            return cp.map(parent -> {
//                Comment        comment  = new Comment(parent);
//                List<Comments> children = new ArrayList<>();
//                getChildren(children, comment.getCoid());
//                comment.setChildren(children);
//                if (BladeKit.isNotEmpty(children)) {
//                    comment.setLevels(1);
//                }
//                return comment;
//            });
//        }
        return null;
    }

    /**
     * 获取该评论下的追加评论
     *
     * @param coid
     * @return
     */
    private void getChildren(List<Comments> list, Integer coid) {
//        List<Comments> cms = new Comments().where("parent", coid).findAll(OrderBy.asc("coid"));
//        if (null != cms) {
//            list.addAll(cms);
//            cms.forEach(c -> getChildren(list, c.getCoid()));
//        }
    }

    /**
     * 根据主键查询评论
     *
     * @param coid
     * @return
     */
    public Comments byId(Integer coid) {
        if (null != coid) {
            // 根据id来查找评论
            Comments comment = commentService.selectCommentByid(coid);
            return comment;
        }
        return null;
    }
}
