package com.educoder.bridge.k8s.model;

import java.util.Date;

public class ScalePlan {
    private Long id;

    private String scaleId;

    private Long priority;

    private Integer podType;

    private String containers;

    private String containersMd5;

    private String tpIds;

    private Date startTime;

    private Date endTime;

    private Integer maxNum;

    private Integer minNum;

    private String status;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScaleId() {
        return scaleId;
    }

    public void setScaleId(String scaleId) {
        this.scaleId = scaleId;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Integer getPodType() {
        return podType;
    }

    public void setPodType(Integer podType) {
        this.podType = podType;
    }

    public String getContainers() {
        return containers;
    }

    public void setContainers(String containers) {
        this.containers = containers;
    }

    public String getContainersMd5() {
        return containersMd5;
    }

    public void setContainersMd5(String containersMd5) {
        this.containersMd5 = containersMd5;
    }

    public String getTpIds() {
        return tpIds;
    }

    public void setTpIds(String tpIds) {
        this.tpIds = tpIds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}