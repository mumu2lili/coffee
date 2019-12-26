package com.educoder.bridge.k8s.model;

import java.util.Date;

public class DeletePod {
    private Long id;

    private String name;

    private String tpiId;

    private Integer podType;

    private String nodeName;

    private String nodeIp;

    private Date deleteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTpiId() {
        return tpiId;
    }

    public void setTpiId(String tpiId) {
        this.tpiId = tpiId;
    }

    public Integer getPodType() {
        return podType;
    }

    public void setPodType(Integer podType) {
        this.podType = podType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}