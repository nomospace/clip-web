package com.clip.web.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "service_request")
@Entity
public class ServiceRequest {

    private String uuid;

    @Id
    @Column(name = "id")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private Date startTime;

    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    private String targetService;

    @Column(name = "target_service")
    public String getTargetService() {
        return targetService;
    }

    public void setTargetService(String targetService) {
        this.targetService = targetService;
    }

    private String targetObj;

    @Column(name = "target_obj")
    public String getTargetObj() {
        return targetObj;
    }

    public void setTargetObj(String targetObj) {
        this.targetObj = targetObj;
    }

    private char action;

    @Column(name = "action")
    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }

    private Long taskId;

    @Column(name = "task_id")
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

}
