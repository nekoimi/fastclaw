package com.nekoimi.fastclaw.mapper;


import com.nekoimi.fastclaw.framework.mybatis.BaseMapper;
import com.nekoimi.fastclaw.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Label Mapper 接口
 *
 * nekoimi  2022-04-29
 */
@Mapper
@Component
public interface LabelMapper extends BaseMapper<Label> {
}
