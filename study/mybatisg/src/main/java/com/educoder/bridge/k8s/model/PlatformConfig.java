package com.educoder.bridge.k8s.model;

import java.util.Date;

public class PlatformConfig {
    private Long id;

    private String platform;

    private String containersMd5;

    private String containers;

    private Date createTime;

    private Date updateTime;

    private String script;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getContainersMd5() {
        return containersMd5;
    }

    public void setContainersMd5(String containersMd5) {
        this.containersMd5 = containersMd5;
    }

    public String getContainers() {
        return containers;
    }

    public void setContainers(String containers) {
        this.containers = containers;
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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}