package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.Actress;
import com.nekoimi.vasashi.mapper.ActressMapper;
import com.nekoimi.vasashi.service.ActressService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * Actress Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "actress-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class ActressServiceImpl extends ReactiveCrudService<ActressMapper, Actress> implements ActressService {
}
