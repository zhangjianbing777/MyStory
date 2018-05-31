package com.nmys.story.extension;

import com.blade.kit.StringKit;
import com.nmys.story.model.entity.Metas;
import org.springframework.stereotype.Component;

/**
 * 后台公共函数
 * <p>
 * Created by 70kg on 2018/5/21.
 */
@Component
public final class AdminCommons {

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

    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    public static String rand_color() {
        int r = StringKit.rand(0, COLORS.length - 1);
        return COLORS[r];
    }

}
