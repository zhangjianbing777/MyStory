package com.nmys.story.controller.admin;

import com.nmys.story.constant.MailConstant;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;

/**
 * Description: 邮件发送控制层
 *
 * @Author 70KG
 * @Date 2019/1/24
 * @Since v2.0
 */
@Controller
@RequestMapping("/article")
public class MailController extends BaseController {


    @RequestMapping(value = "testMail")
    @ResponseBody
    public void goTest() {
        Context context = new Context();
        context.setVariable("id", "64");
        context.setVariable("replyContent","你好我是回复内容。");
        sendEmail(MailConstant.REPLY_NOTICE_TEMPLATE, "zhangjianbing777@gmail.com", MailConstant.MAIL_SUBJECT_1, context);
    }


}
