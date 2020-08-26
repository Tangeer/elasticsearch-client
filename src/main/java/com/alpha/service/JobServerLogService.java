package com.alpha.service;

import com.alpha.entity.BookBean;
import com.alpha.entity.JobServerLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName JobServerLogService
 * @Description TODO
 * @Author Tanger
 * @Date 2020/8/22 11:54
 */
public interface JobServerLogService {

    // 保存
    public JobServerLog save(JobServerLog jobServerLog);
    // 批量保存
    public String saveAll(List<JobServerLog> list);
    // 删除
    public void delete(JobServerLog jobServerLog);
    // 根据id查询
    public Optional<JobServerLog> findById(String id);
    // 查询全部
    public List<JobServerLog> findAll();
    // 根据实例code来查询
    public JobServerLog findByJobInstanceCode(String jobInstanceCode);
    // 根据日期来查询
    public List<JobServerLog> findAllByDate(String date);
    // 带有排序功能的按实例Code来查询
    public List<JobServerLog> findByJobInstanceCodeWithSort(String jobInstanceCode, String sort_field);

}
