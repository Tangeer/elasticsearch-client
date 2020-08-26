package com.alpha.service.impl;


import com.alpha.entity.JobServerLog;
import com.alpha.service.JobServerLogRepository;
import com.alpha.service.JobServerLogService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName JobServerLogImpl
 * @Description TODO
 * @Author Tanger
 * @Date 2020/8/22 11:59
 */
@Service("jobServerLogService")
public class JobServerLogServiceImpl implements JobServerLogService {
    @Autowired
    JobServerLogRepository jobServerLogRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TransportClient client;

    @Override
    public JobServerLog save(JobServerLog jobServerLog) {
        return jobServerLogRepository.save(jobServerLog);
    }

    @Override
    public String saveAll(List<JobServerLog> list) {
        try{
            jobServerLogRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return "现在共有"+ jobServerLogRepository.count() + "条数据";
        }

    }


    @Override
    public void delete(JobServerLog jobServerLog) {
        jobServerLogRepository.delete(jobServerLog);
    }

    @Override
    public Optional<JobServerLog> findById(String id) {
        return jobServerLogRepository.findById(id);
    }

    @Override
    public List<JobServerLog> findAll() {
        Iterable<JobServerLog> allLogs = jobServerLogRepository.findAll();
        List<JobServerLog> list = new ArrayList<JobServerLog>();
        for (JobServerLog allLog : allLogs) {
            list.add(allLog);
        }
        return list;
    }

    @Override
    public JobServerLog findByJobInstanceCode(String jobInstanceCode) {
        return jobServerLogRepository.findByJobInstanceCode(jobInstanceCode);
    }

    @Override
    public List<JobServerLog> findAllByDate(String date) {
        return jobServerLogRepository.findAllByDate(date);
    }

    /**
     * 排序
     * @param jobInstanceCode
     * @param sort_field
     * @return
     */
    @Override
    public List<JobServerLog> findByJobInstanceCodeWithSort(String jobInstanceCode, String sort_field) {
        try {
            List<JobServerLog> list = new ArrayList<JobServerLog>();
            long count = jobServerLogRepository.count();
            // 过滤
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("jobInstanceCode", jobInstanceCode);
            //排序
            FieldSortBuilder fieldSortBuilder = null;
            if (!StringUtils.isEmpty(sort_field)){
                fieldSortBuilder = SortBuilders.fieldSort(sort_field).order(SortOrder.ASC);
            }
           // 分页
            Pageable pageable = PageRequest.of(0, (int)count);
            // 构建查询
            SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQueryBuilder).withSort(fieldSortBuilder).withPageable(pageable).build();

            Page<JobServerLog> search = jobServerLogRepository.search(searchQuery);
            Iterator<JobServerLog> iterator = search.iterator();
            while (iterator.hasNext()){
                JobServerLog jobServerLog = iterator.next();
                list.add(jobServerLog);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
