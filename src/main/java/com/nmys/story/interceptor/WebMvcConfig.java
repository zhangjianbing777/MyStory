package com.nmys.story.interceptor;

import com.nmys.story.utils.TaleUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Description:向mvc中添加自定义组件
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/11 13:35
 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
    }

    /**
     * Description:添加静态资源文件，外部可以直接访问地址
     * Author:70kg
     * Param [registry]
     * Return void
     * Date 2018/5/11 13:37
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + TaleUtils.getUplodFilePath() + "upload/");
        super.addResourceHandlers(registry);
    }
}
