package com.nmys.story.test_gc;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author 70KG
 * @date 2018/9/20
 */
public class TestGc {

    public static void main(String[] args) throws Exception{

        Thread.sleep(5000);
        List<Byte[]> list = new ArrayList();

        while(true){
//            Thread.sleep(1000);
            Byte[] str = new Byte[1024*1024];// 1M
            list.add(str);
            System.out.println(str);
        }
    }

}
