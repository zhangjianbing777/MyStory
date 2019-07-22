package com.nmys.story.autoconfigure.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author zhangjianbing
 * time 2019/7/8
 */
@Import(SwaggerConfig.class)
@ConditionalOnProperty("zhangjianbing.web.swagger.enabled")
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {
    // Spring Auto Configuration
}
