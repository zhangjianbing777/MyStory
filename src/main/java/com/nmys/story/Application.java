package com.nmys.story;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:启动类
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/10 9:38
 */
@SpringBootApplication
@MapperScan("com.nmys.story.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}