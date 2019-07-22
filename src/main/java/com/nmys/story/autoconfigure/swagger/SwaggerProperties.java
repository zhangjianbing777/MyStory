package com.nmys.story.autoconfigure.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger properties
 *
 * @author zhangjianbing
 * time 2019/7/8
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "zhangjianbing.web.swagger", ignoreUnknownFields = false)
@SuppressWarnings("squid:S1068")
public class SwaggerProperties {

    /**
     * 联系方式
     */
    private final Contact contact = new Contact();

    /**
     * 是否启用swagger
     */
    private Boolean enabled = false;

    /**
     * 文档描述
     */
    private String description = "";

    /**
     * 扫描路径
     */
    private String basePath = "";

    /**
     * 扫描路径
     */
    private String basePackage = "com.ryan";

    /**
     * 当前版本号
     */
    private String version = "v1.0";

    @Setter
    @Getter
    public static class Contact {
        private String name;
        private String url;
        private String email;
    }

}
