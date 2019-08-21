package com.nmys.story.config;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * mystory应用上下文
 *
 * @author zhangjianbing
 * time 2019/8/21
 */
public class MyStoryContext {

    public static final ThreadLocal<Map<String, Object>> contextData = new ThreadLocal<>();

    public static final String MENU_LIST = "menuList";

    /**
     * 将数据存入上下文中
     *
     * @param key   指定的key
     * @param value 上下文的内容
     */
    public static void put(String key, Object value) {
        Map<String, Object> data = contextData.get();
        if (data == null) {
            data = Maps.newHashMap();
            contextData.set(data);
        }
        data.put(key, value);
    }

    /**
     * 根据key获取上下文中的内容
     *
     * @param clazz 对象类型
     * @param key   指定的key
     * @param <T>   对象泛型
     * @return T
     */
    public static <T> T getObj(Class<T> clazz, String key) {
        if (contextData.get() == null) {
            return null;
        }
        Object value = contextData.get().get(key);
        if (value == null) {
            return null;
        }
        return (T) value;
    }

    /**
     * 清除上下文
     */
    public static void remove() {
        contextData.remove();
    }


}
