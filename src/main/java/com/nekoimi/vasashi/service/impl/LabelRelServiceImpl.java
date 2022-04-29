package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.LabelRel;
import com.nekoimi.vasashi.mapper.LabelRelMapper;
import com.nekoimi.vasashi.service.LabelRelService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * LabelRel Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "labelrel-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class LabelRelServiceImpl extends ReactiveCrudService<LabelRelMapper, LabelRel> implements LabelRelService {
}
