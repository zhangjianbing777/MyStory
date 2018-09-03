package com.nmys.story.exception;

import com.nmys.story.model.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.Subject;

/**
 * Description:全局异常处理
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/10 16:48
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = TipException.class)
    public String tipException(Exception e) {
        logger.error("find exception:e={}", e.getMessage());
        e.printStackTrace();
        return "comm/error_500";
    }


    /**
     * Description: 无权限异常跳转403页面
     * Author:70KG
     * Param []
     * Return java.lang.String
     * Date 2018/9/3 16:03
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException() {
        Users user = (Users) SecurityUtils.getSubject().getPrincipal();
        logger.error("用户：" + user.getUsername() + "进行了一次无权限操作！");
        return "comm/error_403";
    }

    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e) {
        logger.error("find exception:e={}", e.getMessage());
        e.printStackTrace();
        return "comm/error_404";
    }

}
