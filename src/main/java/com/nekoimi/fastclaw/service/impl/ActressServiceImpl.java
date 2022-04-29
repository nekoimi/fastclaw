package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.Actress;
import com.nekoimi.fastclaw.mapper.ActressMapper;
import com.nekoimi.fastclaw.service.ActressService;
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
