package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.User;
import com.nekoimi.fastclaw.mapper.UserMapper;
import com.nekoimi.fastclaw.service.UserService;
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
