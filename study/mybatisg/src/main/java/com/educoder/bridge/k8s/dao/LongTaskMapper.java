package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.LongTask;

public interface LongTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LongTask record);

    int insertSelective(LongTask record);

    LongTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LongTask record);

    int updateByPrimaryKey(LongTask record);
}