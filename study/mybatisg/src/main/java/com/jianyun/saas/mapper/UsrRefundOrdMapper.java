package com.jianyun.saas.mapper;

import com.jianyun.saas.model.UsrRefundOrd;

public interface UsrRefundOrdMapper {
    int deleteByPrimaryKey(String refundId);

    int insert(UsrRefundOrd record);

    int insertSelective(UsrRefundOrd record);

    UsrRefundOrd selectByPrimaryKey(String refundId);

    int updateByPrimaryKeySelective(UsrRefundOrd record);

    int updateByPrimaryKey(UsrRefundOrd record);
}