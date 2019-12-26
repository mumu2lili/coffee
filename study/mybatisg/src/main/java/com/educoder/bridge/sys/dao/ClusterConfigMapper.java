package com.educoder.bridge.sys.dao;

import com.educoder.bridge.sys.model.ClusterConfig;

public interface ClusterConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClusterConfig record);

    int insertSelective(ClusterConfig record);

    ClusterConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClusterConfig record);

    int updateByPrimaryKey(ClusterConfig record);
}