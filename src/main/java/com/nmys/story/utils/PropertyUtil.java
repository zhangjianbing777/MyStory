package com.nmys.story.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Description:properties工具类
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/31 13:15
 */
public class PropertyUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

    private static Properties props;

    // 类加载的时候加载
    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        logger.info("开始加载settingConfig.properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            //通过类加载器进行获取properties
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("settingConfig.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("settingConfig.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("settingConfig.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
    }

    /**
     * Description:根据key来获取value
     * Author:70kg
     * Param [key]
     * Return java.lang.String
     * Date 2018/5/31 13:15
     */
    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

}
