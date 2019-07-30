package com.educoder.bridge.alarm.dao;

import com.educoder.bridge.alarm.model.Alarm;

public interface AlarmMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Alarm record);

	int insertSelective(Alarm record);

	Alarm selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Alarm record);

	int updateByPrimaryKey(Alarm record);
}