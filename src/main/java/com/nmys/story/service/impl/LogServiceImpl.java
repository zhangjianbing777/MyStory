package com.nmys.story.service.impl;

import com.nmys.story.mapper.LogMapper;
import com.nmys.story.model.entity.Logs;
import com.nmys.story.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void visitSetLog(Logs log) {
        logMapper.visitSetLog(log);
    }

}
