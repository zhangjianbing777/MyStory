package com.nmys.story.test_ip_resttemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nmys.story.model.dto.IpBean;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * description
 *
 * @author 70KG
 * @date 2018/7/27
 */
public class RestTest {

    public static final String IP = "120.82.217.49";

    @Test
    public void Test01() {
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + IP;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String body = entity.getBody();
        IpBean ipBean = JSON.parseObject(body, IpBean.class);
        String country = ipBean.getData().get("country");
        String region = ipBean.getData().get("region");
        String city = ipBean.getData().get("city");
        String code = ipBean.getCode();
//        IpBean ipBean = JSONObject.parseObject(body, IpBean.class);
//        System.out.println(code);
//        System.out.println(body);
//        IpBean ipBean = JSONObject.toJavaObject(body, IpBean.class);
//        String data = (String) jsonObject.get("data");
        System.out.println(code);
        System.out.println(country);
//        JSONObject data = JSONObject.parseObject((String) jsonObject.get("data"));
//        String str = data.get("country") + "-" + data.get("region") + "-" + data.get("city");
//        System.out.println(str);
    }

}
