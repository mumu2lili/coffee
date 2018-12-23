package com.jianyun.saas.mapper;

import com.jianyun.saas.model.WxParam;

public interface WxParamMapper {
    int insert(WxParam record);

    int insertSelective(WxParam record);
}