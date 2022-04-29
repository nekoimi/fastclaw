package com.nekoimi.vasashi.mapper;


import com.nekoimi.vasashi.framework.mybatis.BaseMapper;
import com.nekoimi.vasashi.entity.Actress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Actress Mapper 接口
 *
 * nekoimi  2022-04-29
 */
@Mapper
@Component
public interface ActressMapper extends BaseMapper<Actress> {
}
