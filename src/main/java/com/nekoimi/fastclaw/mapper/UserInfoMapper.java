package com.nekoimi.fastclaw.mapper;


import com.nekoimi.fastclaw.framework.mybatis.BaseMapper;
import com.nekoimi.fastclaw.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * UserInfo Mapper 接口
 *
 * nekoimi  2022-04-29
 */
@Mapper
@Component
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
