package com.nmys.story.testredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    // 存入key
    @Test
    public void m0() {

        for (int i = 0; i <= 50; i++) {
            Map map = new HashMap();
            map.put("name", "zhangsan");
            map.put("age", "25");
            stringRedisTemplate.opsForHash().putAll("test_" + i, map);
            stringRedisTemplate.expire("test_" + i, 300, TimeUnit.SECONDS);// 5分钟超时
            System.out.println("test_------" + i + "------redis存入成功!");
        }

    }


    // 取出key
    @Test
    public void m1() {
        for (int j = 0; j <= 50; j++) {
            try {
                stringRedisTemplate.opsForHash().entries("test_" + j);
                System.out.println("test_------" + j + "------redis取出成功!");
            } catch (Exception e) {
                System.err.println("test_------" + j + "------redis取出失败!");
            }
        }
    }


    // 删除key
    @Test
    public void m2() {
        for (int i = 0; i <= 50; i++) {
            stringRedisTemplate.delete("test_" + i);
            System.out.println("test_------" + i + "------redis删除成功!");
        }
    }

}
