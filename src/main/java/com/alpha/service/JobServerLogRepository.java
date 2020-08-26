package com.alpha.service;

import com.alpha.entity.JobServerLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface JobServerLogRepository extends ElasticsearchRepository<JobServerLog, String> {

    public JobServerLog findByJobInstanceCode(String jobInstanceCode);

    public List<JobServerLog> findAllByDate(String date);








}
