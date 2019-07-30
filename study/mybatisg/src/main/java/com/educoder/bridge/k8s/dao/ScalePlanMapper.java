package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.ScalePlan;

public interface ScalePlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScalePlan record);

    int insertSelective(ScalePlan record);

    ScalePlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScalePlan record);

    int updateByPrimaryKey(ScalePlan record);
}