package com.example.taurus.repository;

import com.example.taurus.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.taurus.model.domain.Logs;


import java.util.List;

public interface LogsRepository extends BaseRepository<Logs, Long> {
    /**
     * 查询最近的五条数据
     */
    @Query(value = "Select * from taurus_logs order by log_created desc limit 5", nativeQuery = true)
    List<Logs> findTopFive();
}
