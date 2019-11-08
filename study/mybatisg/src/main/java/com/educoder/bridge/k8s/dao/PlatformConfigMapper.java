package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.PlatformConfig;

public interface PlatformConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformConfig record);

    int insertSelective(PlatformConfig record);

    PlatformConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformConfig record);

    int updateByPrimaryKeyWithBLOBs(PlatformConfig record);

    int updateByPrimaryKey(PlatformConfig record);
}