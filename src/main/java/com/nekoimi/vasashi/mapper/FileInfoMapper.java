package com.nekoimi.vasashi.mapper;


import com.nekoimi.vasashi.framework.mybatis.BaseMapper;
import com.nekoimi.vasashi.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * FileInfo Mapper 接口
 *
 * nekoimi  2022-04-26
 */
@Mapper
@Component
public interface FileInfoMapper extends BaseMapper<FileInfo> {
}
