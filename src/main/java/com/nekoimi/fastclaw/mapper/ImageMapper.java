package com.nekoimi.fastclaw.mapper;


import com.nekoimi.fastclaw.framework.mybatis.BaseMapper;
import com.nekoimi.fastclaw.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Image Mapper 接口
 *
 * nekoimi  2022-04-29
 */
@Mapper
@Component
public interface ImageMapper extends BaseMapper<Image> {
}
