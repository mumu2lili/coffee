package com.educoder.bridge.alarm.dao;

import com.educoder.bridge.alarm.model.AlarmRule;

public interface AlarmRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlarmRule record);

    int insertSelective(AlarmRule record);

    AlarmRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlarmRule record);

    int updateByPrimaryKey(AlarmRule record);
}