package com.piggy.stock.dict.dao;

import com.piggy.stock.dict.domain.StockDict;

public interface StockDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockDict record);

    int insertSelective(StockDict record);

    StockDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockDict record);

    int updateByPrimaryKey(StockDict record);
}