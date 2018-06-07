package com.nmys.story.test_ip;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) throws IOException {

        JSONObject jsonObject = JSONObject.parseObject(getData());
        System.out.println("这个是用JSONObject的parseObject方法来解析JSON字符串!!!");
//        System.out.println(jsonObject.get("country"));
//        System.out.println(jsonObject.get("city"));
//        System.out.println(jsonObject.get("province"));

        for (Object map : jsonObject.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
        }

//        System.out.println(getData());
    }

    public static String getData() throws IOException {
        // 创建指定url的url对象,这里的地址是:淘宝商品搜索建议
        URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=192.168.1.1");
        // 创建http链接对象
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // 设置请求方式
        con.setRequestMethod("POST");
        // 打开链接,上一步和该步骤作用相同，可以省略
        con.connect();

        // 获取请求返回内容并设置编码为UTF-8
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        // 将返回数据拼接为字符串
        StringBuffer sb = new StringBuffer();
        // 临时字符串
        String temp = null;
        // 获取数据
        while ((temp = reader.readLine()) != null) {
            sb.append(temp);
        }
        // 关闭流
        reader.close();
        return sb.toString();
    }

    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

}
