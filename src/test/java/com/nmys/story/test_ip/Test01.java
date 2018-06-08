package com.nmys.story.test_ip;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) throws Exception {

//        JSONObject jsonObject = JSONObject.parseObject(getJsonString("200.25.233.22"));
//        System.out.println("这个是用JSONObject的parseObject方法来解析JSON字符串!!!");
//        System.out.println(jsonObject.get("country"));
//        System.out.println(jsonObject.get("city"));
//        System.out.println(jsonObject.get("province"));

//        for (Object map : jsonObject.entrySet()){
//            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
//        }

//        System.out.println(getData());


        String info = getSimplePositionInfo("201.150.149.4");
        System.out.println(info);

    }

    public static String getSimplePositionInfo(String ip) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(getJsonString(ip));
        Object province = jsonObject.get("province");
        Object city = jsonObject.get("city");
        String sb = "";
        if (province != null && province != "") {
            sb += (String) province;
        }
        if (city != null && city != "") {
            sb += (String) city;
        }
        if (StringUtils.isNotBlank((String) province) && StringUtils.isNotBlank((String) city)) {
            String a = (String) province;
            String b = (String) city;
            if (a.equals(b)) {
                sb = b;
            }
        }
        return sb;
    }

    public static String getPositionInfo(String ip) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(getJsonString(ip));
        // 国家-省-市
        return jsonObject.get("country") + "-" + jsonObject.get("province") + "-" + jsonObject.get("city");
    }


    public static String getJsonString(String ip) throws Exception {
        // 创建指定url的url对象,地址是:新浪免费接口
        URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip);
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
