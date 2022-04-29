package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.UserInfo;
import com.nekoimi.vasashi.mapper.UserInfoMapper;
import com.nekoimi.vasashi.service.UserInfoService;
import org.springframework.cache.annotation.CacheConfig;
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
