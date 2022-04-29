package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.User;
import com.nekoimi.vasashi.mapper.UserMapper;
import com.nekoimi.vasashi.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * User Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "user-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class UserServiceImpl extends ReactiveCrudService<UserMapper, User> implements UserService {
}
