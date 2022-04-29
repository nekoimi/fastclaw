package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.Magnet;
import com.nekoimi.vasashi.mapper.MagnetMapper;
import com.nekoimi.vasashi.service.MagnetService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * Magnet Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "magnet-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class MagnetServiceImpl extends ReactiveCrudService<MagnetMapper, Magnet> implements MagnetService {
}
