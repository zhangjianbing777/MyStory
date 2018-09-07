package com.nmys.story.extension;

import com.nmys.story.model.entity.Metas;
import com.nmys.story.model.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 后台公共函数
 * <p>
 * Created by 70kg on 2018/5/21.
 */
@Component
public final class AdminCommons {

    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 判断category和cat的交集
     *
     * @param cats
     * @return
     */
    public static boolean exist_cat(Metas category, String cats) {
        String[] arr = null != cats ? cats.split(",") : null;
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
    }


    public static String rand_color() {
        Random rand = new Random();
        int r = rand.nextInt(9);
        return COLORS[r];
    }

    /**
     * Description: 获取登录人姓名
     * Author:70KG
     * Return java.lang.String
     * Date 2018/9/7 15:32
     */
    public static String getLoginUserName() {
        Users user = (Users) SecurityUtils.getSubject().getPrincipal();
        if (null != user) {
            return user.getUsername();
        }
        return "guy";
    }


}
