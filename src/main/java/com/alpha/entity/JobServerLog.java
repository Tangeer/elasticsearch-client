package com.alpha.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @ClassName JobServerLog
 * @Description TODO
 * @Author Tanger
 * @Date 2020/8/21 10:54
 */
@Document(indexName = "job_server_log", type = "_doc")
@Data
public class JobServerLog implements Serializable {
    /**
     * 需要添加 @Id 标识主键
     */
    // 记录ID
    @Id
    private String id;
    // 任务实例代码
    private String jobInstanceCode;
    // 日志信息
    private String message;
    // 记录日期
    private String date;
    // 记录时间
    private String datetime;
    // 记录时间戳
    private long timestamp;
    // 任务类型
    private String jobType;
    // 任务所属者
    private String owner;
    // 任务名称
    private String jobName;

    public JobServerLog(String id, String jobInstanceCode, String message, String date, String datetime, long timestamp, String jobType, String owner, String jobName) {
        this.id = id;
        this.jobInstanceCode = jobInstanceCode;
        this.message = message;
        this.date = date;
        this.datetime = datetime;
        this.timestamp = timestamp;
        this.jobType = jobType;
        this.owner = owner;
        this.jobName = jobName;
    }

    public JobServerLog() {
    }
}
