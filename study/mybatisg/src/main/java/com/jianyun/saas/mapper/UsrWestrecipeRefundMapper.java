package com.jianyun.saas.mapper;

import com.jianyun.saas.model.UsrWestrecipeRefund;
import com.jianyun.saas.model.UsrWestrecipeRefundKey;

public interface UsrWestrecipeRefundMapper {
    int deleteByPrimaryKey(UsrWestrecipeRefundKey key);

    int insert(UsrWestrecipeRefund record);

    int insertSelective(UsrWestrecipeRefund record);

    UsrWestrecipeRefund selectByPrimaryKey(UsrWestrecipeRefundKey key);

    int updateByPrimaryKeySelective(UsrWestrecipeRefund record);

    int updateByPrimaryKey(UsrWestrecipeRefund record);
}