package com.educoder.bridge.k8s.model;

import java.util.Date;

public class BridgePod {
    private Long id;

    private String name;

    private String tpiId;

    private Date k8sCreateTime;

    private Date deleteTime;

    private String secKey;

    private Date requestTime;

    private Double pull;

    private Double createPod;

    private Double execute;

    private Double evaluateAllTime;

    private String nodeName;

    private String nodeIp;

    private String downloadStatus;

    private String createPodStatus;

    private String compileStatus;

    private String runStatus;

    private String status;

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

    public Date getK8sCreateTime() {
        return k8sCreateTime;
    }

    public void setK8sCreateTime(Date k8sCreateTime) {
        this.k8sCreateTime = k8sCreateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Double getPull() {
        return pull;
    }

    public void setPull(Double pull) {
        this.pull = pull;
    }

    public Double getCreatePod() {
        return createPod;
    }

    public void setCreatePod(Double createPod) {
        this.createPod = createPod;
    }

    public Double getExecute() {
        return execute;
    }

    public void setExecute(Double execute) {
        this.execute = execute;
    }

    public Double getEvaluateAllTime() {
        return evaluateAllTime;
    }

    public void setEvaluateAllTime(Double evaluateAllTime) {
        this.evaluateAllTime = evaluateAllTime;
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

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getCreatePodStatus() {
        return createPodStatus;
    }

    public void setCreatePodStatus(String createPodStatus) {
        this.createPodStatus = createPodStatus;
    }

    public String getCompileStatus() {
        return compileStatus;
    }

    public void setCompileStatus(String compileStatus) {
        this.compileStatus = compileStatus;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}