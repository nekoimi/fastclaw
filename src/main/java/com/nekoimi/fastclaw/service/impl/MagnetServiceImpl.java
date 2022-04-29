package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.Magnet;
import com.nekoimi.fastclaw.mapper.MagnetMapper;
import com.nekoimi.fastclaw.service.MagnetService;
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
