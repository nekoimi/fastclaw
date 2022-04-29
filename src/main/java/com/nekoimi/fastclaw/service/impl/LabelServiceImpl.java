package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.Label;
import com.nekoimi.fastclaw.mapper.LabelMapper;
import com.nekoimi.fastclaw.service.LabelService;
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
