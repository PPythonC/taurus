package com.example.taurus.service;

import com.example.taurus.model.domain.Logs;
import com.example.taurus.service.base.CrudService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LogsService extends CrudService<Logs,Long> {
    void save(String logTitle, String logContent, HttpServletRequest request);
    List<Logs> findLogsLatest();
}
