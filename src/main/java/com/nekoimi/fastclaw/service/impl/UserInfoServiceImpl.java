package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.UserInfo;
import com.nekoimi.fastclaw.mapper.UserInfoMapper;
import com.nekoimi.fastclaw.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * UserInfo Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "userinfo-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class UserInfoServiceImpl extends ReactiveCrudService<UserInfoMapper, UserInfo> implements UserInfoService {
}
