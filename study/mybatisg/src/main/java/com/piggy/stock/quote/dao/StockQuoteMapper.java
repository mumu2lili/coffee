package com.piggy.stock.quote.dao;

import com.piggy.stock.quote.domain.StockQuote;

public interface StockQuoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockQuote record);

    int insertSelective(StockQuote record);

    StockQuote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockQuote record);

    int updateByPrimaryKey(StockQuote record);
}