package com.educoder.bridge.alarm.dao;

import com.educoder.bridge.alarm.model.FirePolice;

public interface FirePoliceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FirePolice record);

    int insertSelective(FirePolice record);

    FirePolice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FirePolice record);

    int updateByPrimaryKey(FirePolice record);
}