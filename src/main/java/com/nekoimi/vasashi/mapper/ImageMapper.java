package com.nekoimi.vasashi.mapper;


import com.nekoimi.vasashi.framework.mybatis.BaseMapper;
import com.nekoimi.vasashi.entity.Image;
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
