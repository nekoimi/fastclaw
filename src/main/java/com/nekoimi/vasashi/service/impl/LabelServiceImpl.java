package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.Label;
import com.nekoimi.vasashi.mapper.LabelMapper;
import com.nekoimi.vasashi.service.LabelService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * Label Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "label-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class LabelServiceImpl extends ReactiveCrudService<LabelMapper, Label> implements LabelService {
}
