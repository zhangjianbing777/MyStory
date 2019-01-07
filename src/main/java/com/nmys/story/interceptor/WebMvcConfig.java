package com.nmys.story.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Description:向mvc中添加自定义组件
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/11 13:35
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
        registry.addInterceptor(annotationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AnnotationInterceptor annotationInterceptor() {
        return new AnnotationInterceptor();
    }

//    /**
//     * Description:添加静态资源文件，外部可以直接访问地址
//     * Author:70kg
//     * Param [registry]
//     * Return void
//     * Date 2018/5/11 13:37
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + TaleUtils.getUplodFilePath() + "upload/");
//        super.addResourceHandlers(registry);
//    }
}
