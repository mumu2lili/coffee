package com.jianyun.saas.mapper;

import com.jianyun.saas.model.UsrEastrecipeRefund;
import com.jianyun.saas.model.UsrEastrecipeRefundKey;

public interface UsrEastrecipeRefundMapper {
    int deleteByPrimaryKey(UsrEastrecipeRefundKey key);

    int insert(UsrEastrecipeRefund record);

    int insertSelective(UsrEastrecipeRefund record);

    UsrEastrecipeRefund selectByPrimaryKey(UsrEastrecipeRefundKey key);

    int updateByPrimaryKeySelective(UsrEastrecipeRefund record);

    int updateByPrimaryKey(UsrEastrecipeRefund record);
}