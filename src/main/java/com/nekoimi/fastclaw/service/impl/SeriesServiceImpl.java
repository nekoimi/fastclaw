package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.Series;
import com.nekoimi.fastclaw.mapper.SeriesMapper;
import com.nekoimi.fastclaw.service.SeriesService;
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
