package com.nmys.story.test_ip;

import org.apache.commons.lang3.StringUtils;
import sun.net.www.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class Test01 {

    /**
     * ipSearchUrl=http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=（这是新浪的接口地址）
     * 在项目中新浪的接口地址没有直接写死，而是去读属性文件。
     */
//    public static String getIpInfoBySina(String ip) {
//        // 读取属性文件(属性文件key-value和格式)
//        final String PROP_IPSEARCHURL = "ipSearchUrl";
//        final String RET_SUCCESS = "1";
//        final String RET = "ret";
//        final String PROVINCE = "province";
//        final String CITY = "city";
//        final String DISTRICT = "district";
//        final String ISP = "isp";
//        String ipAddress = "";
//        if (StringUtils.isBlank(ip)) {
//            return null;
//        }
//        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";//这个是从属性文件中获取url，即新浪接口地址
//        if (StringUtils.isNotBlank(url)) {
//            // 地址
//            String path = url + ip;
//            HttpClient httpClient = new HttpClient();
//            Map<String, String> paramMap = new HashMap<String, String>();
//            String remoteIpInfo = "";
//
//            try {
//                remoteIpInfo = HttpClientUtil.request(httpClient, path, paramMap, "sina");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (StringUtils.isNotBlank(remoteIpInfo)) {
//                String _ret = searchValue(remoteIpInfo, RET);
//                if (RET_SUCCESS.equals(_ret)) {
//                    String provinceName = searchValue(remoteIpInfo, PROVINCE);
//                    String cityName = searchValue(remoteIpInfo, CITY);
//                    String district = searchValue(remoteIpInfo, DISTRICT);
//                    String isp = searchValue(remoteIpInfo, ISP);
//                    ipAddress = provinceName + cityName + district + isp;
//                }
//            }
//        }
//        return ipAddress;
//    }
//
//    private static String searchValue(String remoteIpInfo, String key) {
//        String _value = "";
//        if (StringUtils.isNotBlank(remoteIpInfo)) {
//            _value = StringUtils.substringBetween(remoteIpInfo, "\"" + key + "\":", ",");
//            if (StringUtils.isNotBlank(_value)) {
//                _value = UnicodeUtils.decodeUnicode(_value);
//            }
//        }
//        return _value;
//    }

}
