package com.educoder.bridge.k8s.dao;

import com.educoder.bridge.k8s.model.LongTaskReq;

public interface LongTaskReqMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LongTaskReq record);

    int insertSelective(LongTaskReq record);

    LongTaskReq selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LongTaskReq record);

    int updateByPrimaryKeyWithBLOBs(LongTaskReq record);

    int updateByPrimaryKey(LongTaskReq record);
}