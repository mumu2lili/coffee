package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.BridgePod;

public interface BridgePodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BridgePod record);

    int insertSelective(BridgePod record);

    BridgePod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BridgePod record);

    int updateByPrimaryKey(BridgePod record);
}