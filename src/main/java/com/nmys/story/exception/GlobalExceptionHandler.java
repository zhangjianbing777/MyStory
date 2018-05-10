package com.nmys.story.exception;

import com.blade.mvc.handler.DefaultExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Description:全局异常处理
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/10 16:48
 */
@ControllerAdvice
public class GlobalExceptionHandler extends DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = TipException.class)
    public String tipException(Exception e) {
        logger.error("find exception:e={}", e.getMessage());
        e.printStackTrace();
        return "comm/error_500";
    }


    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e) {
        logger.error("find exception:e={}", e.getMessage());
        e.printStackTrace();
        return "comm/error_404";
    }

}
