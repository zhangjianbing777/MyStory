package com.nmys.story.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 缓存以及线程配置类
 *
 * @Author 70KG
 * @Date 2019/1/24
 * @Since v2.0
 */
@Configuration
public class CacheConfig {

    /**
     * Description: 邮件发送线程池，可扩大
     * Author:70KG
     * Date 2019/1/24
     * Version v2.0
     */
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(5);
    }

}
