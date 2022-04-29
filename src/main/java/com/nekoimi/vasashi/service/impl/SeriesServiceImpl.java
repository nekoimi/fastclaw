package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.Series;
import com.nekoimi.vasashi.mapper.SeriesMapper;
import com.nekoimi.vasashi.service.SeriesService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * Series Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "series-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class SeriesServiceImpl extends ReactiveCrudService<SeriesMapper, Series> implements SeriesService {
}
