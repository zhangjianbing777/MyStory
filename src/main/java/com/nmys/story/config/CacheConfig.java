package com.nmys.story.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: 缓存以及线程配置类
 *
 * @Author 70KG
 * @Date 2019/1/24
 * @Since v2.0
 */
@Configuration
public class CacheConfig {

    // 查看服务器的cpu核数
    final int cpu = Runtime.getRuntime().availableProcessors();
    // 线程池中的好核心线程数
    final int corePoolSize = cpu + 1;
    // 线程池中最大线程数
    final int maximumPoolSize = cpu * 2 + 1;
    // 线程的空闲时间
    final long keepAliveTime = 1L;
    // 时间单位
    final TimeUnit timeUnit = TimeUnit.SECONDS;
    // 队列的长度
    final int maxQueueNum = 128;

    /**
     * Description: 邮件发送线程池，可扩大
     * 不要直接new LinkedBlockingQueue() 这样会导致无界队列，使maximumPoolSize参数不起作用
     * 从而导致任务的响应时间随任务的堆积，而增加。
     * Version v2.0
     */
    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, new LinkedBlockingQueue(maxQueueNum));
    }

}
