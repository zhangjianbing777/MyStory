package com.nmys.story.service.impl;

import com.nmys.story.mapper.LogMapper;
import com.nmys.story.model.entity.Logs;
import com.nmys.story.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    @Transactional
    public void setUserLog(Logs log) {
        logMapper.visitSetLog(log);
    }

    @Override
    public List<Logs> searchVisitLogByAction(String action) {
        return logMapper.searchVisitLogByAction(action);
    }

    @Override
    public List<Logs> getLogsByAction(String action) {
        return logMapper.getLogsByAction(action);
    }

    @Override
    @Transactional
    public void deleteLogById(Integer id) {
        logMapper.deleteLogById(id);
    }

}
