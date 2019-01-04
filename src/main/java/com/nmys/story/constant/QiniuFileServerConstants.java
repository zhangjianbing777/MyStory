package com.nmys.story.constant;

import com.qiniu.common.Zone;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class QiniuFileServerConstants {

    /** 七牛服务器秘钥accesskey **/
    public static final String ACCESSKEY = "";
    /** 七牛服务器秘钥secretkey **/
    public static final String SECRETKEY = "";
    /** 七牛服务器中对应的存储空间名字 **/
    public static final String BUCKET = "mystory";
    /** 测试的域名地址 **/
    public static final String DOMIAN_NAME = "www.nmyswls.com";
    /** 文章详情的路径 **/
    public static final String LOCATION_1 = "nmyswls/article/image";
    /** 其它图片的路径 **/
    public static final String LOCATION_2 = "nmyswls/other/image";


    /**
     * 定义七牛服务器存储的区域
     * 华东zone0()、华北zone1()、华南zone2()、北美zoneNa0()
     */
    public static final Zone ZONE_AREA = Zone.zone1();// 华北

    public static void download(String targetURL) {
        try {
            URL url = new URL(targetURL);
            //创建读取url流对象
            InputStream in = url.openStream();
            File dir = new File("D:\\qiniu_download");
            String contractName = "七牛下载";
            String fileName = contractName + "_" + String.valueOf(System.currentTimeMillis()) + ".pdf";
            File file = new File(dir, fileName);
            FileOutputStream out = new FileOutputStream(file);
            Streams.copy(in, out, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
