package com.jianyun.saas.mapper;

import com.jianyun.saas.model.WxComponent;

public interface WxComponentMapper {
    int insert(WxComponent record);

    int insertSelective(WxComponent record);
}