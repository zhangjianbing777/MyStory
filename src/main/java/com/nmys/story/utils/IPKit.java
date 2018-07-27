package com.nmys.story.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nmys.story.model.dto.IpBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/**
 * Description:IP工具类
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/11 17:30
 */
public class IPKit {

    /**
     * Description:获取客户端ip地址(可以穿透代理)
     * Author:70kg
     * Param [request]
     * Return java.lang.String
     * Date 2018/5/16 10:25
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @return 本机IPSocketException
     * @throws SocketException
     */
    public static String getRealIp() throws SocketException {
        // 本地IP，如果没有配置外网IP则返回它
        String localip = null;
        // 外网IP
        String netip = null;

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        // 是否找到外网IP
        boolean finded = false;
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                // 外网IP
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                    // 内网IP
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                    localip = ip.getHostAddress();
                }
            }
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }


    /**
     * Description: 获取简单地址
     * Author:70kg
     * Param [ip]
     * Return java.lang.String
     * Date 2018/6/8 13:58
     */
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

    /**
     * Description: 获取完整地址
     * Author:70kg
     * Param [ip]
     * Return java.lang.String
     * Date 2018/6/8 13:59
     */
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

    // ---------------------------------------------调用淘宝ip接口-----------------------------------------------------

    /**
     * Description: 调用淘宝ip接口
     * Author:70KG
     * Param [ip]
     * Return java.lang.String
     * Date 2018/7/27 11:22
     */
    public static String getIpInformationFromTaoBao(String ip) {
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String body = entity.getBody();
        IpBean ipBean = JSON.parseObject(body, IpBean.class);
        String country = ipBean.getData().get("country");
        String region = ipBean.getData().get("region");
        String city = ipBean.getData().get("city");
        // 响应码
        String code = ipBean.getCode();
        String position = "";
        if(region.equals(city)){
            position= country + "-" + region;
            return position;
        }
        position = country + "-" + region + "-" + city;
        return position;
    }
}
