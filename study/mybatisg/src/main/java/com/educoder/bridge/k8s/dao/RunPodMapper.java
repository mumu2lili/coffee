package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.RunPod;

public interface RunPodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RunPod record);

    int insertSelective(RunPod record);

    RunPod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RunPod record);

    int updateByPrimaryKey(RunPod record);
}