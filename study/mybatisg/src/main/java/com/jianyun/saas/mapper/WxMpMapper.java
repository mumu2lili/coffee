package com.jianyun.saas.mapper;

import com.jianyun.saas.model.WxMp;

public interface WxMpMapper {
    int insert(WxMp record);

    int insertSelective(WxMp record);
}