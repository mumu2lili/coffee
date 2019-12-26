package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.DeletePod;

public interface DeletePodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeletePod record);

    int insertSelective(DeletePod record);

    DeletePod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeletePod record);

    int updateByPrimaryKey(DeletePod record);
}