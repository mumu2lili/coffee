package com.jianyun.saas.mapper;

import com.jianyun.saas.model.UsrCurerecipeRefund;
import com.jianyun.saas.model.UsrCurerecipeRefundKey;

public interface UsrCurerecipeRefundMapper {
    int deleteByPrimaryKey(UsrCurerecipeRefundKey key);

    int insert(UsrCurerecipeRefund record);

    int insertSelective(UsrCurerecipeRefund record);

    UsrCurerecipeRefund selectByPrimaryKey(UsrCurerecipeRefundKey key);

    int updateByPrimaryKeySelective(UsrCurerecipeRefund record);

    int updateByPrimaryKey(UsrCurerecipeRefund record);
}