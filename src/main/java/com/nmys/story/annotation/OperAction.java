package com.nmys.story.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 创建拦截方法的注解
 * author: Allen 70KG
 * From: www.nmyswls.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperAction {

    String value() default "";

}
