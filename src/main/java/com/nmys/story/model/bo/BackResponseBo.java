package com.nmys.story.model.bo;

import java.io.Serializable;

/**
 * Description:系统设置的返回参数
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/31 10:05
 */
public class BackResponseBo implements Serializable {

    private String attachPath;

    private String themePath;

    private String sqlPath;

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getThemePath() {
        return themePath;
    }

    public void setThemePath(String themePath) {
        this.themePath = themePath;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

}
