package com.nmys.story.mapper;

import com.nmys.story.model.entity.Logs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {

    void visitSetLog(Logs log);

    List<Logs> searchVisitLogByAction(@Param("action") String action);

    List<Logs> getLogsByAction(@Param("action") String action);

    void deleteLogById(@Param("id") Integer id);
}
