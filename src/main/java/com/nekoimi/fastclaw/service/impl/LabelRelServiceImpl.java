package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.LabelRel;
import com.nekoimi.fastclaw.mapper.LabelRelMapper;
import com.nekoimi.fastclaw.service.LabelRelService;
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
