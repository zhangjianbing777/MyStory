package com.nmys.story.test_date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description
 *
 * @author 70KG
 * @date 2018/8/14
 */
public class Test01 {

    @Test
    public void m1() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String s = format.format(new Date());
        System.out.println(s);
    }

}
