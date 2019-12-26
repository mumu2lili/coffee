package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.LongTaskResult;

public interface LongTaskResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LongTaskResult record);

    int insertSelective(LongTaskResult record);

    LongTaskResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LongTaskResult record);

    int updateByPrimaryKey(LongTaskResult record);
}