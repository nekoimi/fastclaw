package com.nekoimi.fastclaw.mapper;


import com.nekoimi.fastclaw.framework.mybatis.BaseMapper;
import com.nekoimi.fastclaw.entity.Actress;
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
