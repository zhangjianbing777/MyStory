package com.nmys.story.service;

import com.nmys.story.model.entity.Logs;

import java.util.List;

public interface ILogService {

    void setUserLog(Logs log);

    /**
     * Description: 查询访客记录
     * author: itachi
     * Date: 2018/6/7 下午8:30
     */
    List<Logs> searchVisitLogByAction(String action);

    /**
     * Description: 根据行为查询日志
     * author: 70KG
     */
    List<Logs> getLogsByAction(String action);

    void deleteLogById(Integer id);

}
