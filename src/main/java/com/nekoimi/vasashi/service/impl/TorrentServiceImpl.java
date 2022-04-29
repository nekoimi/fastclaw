package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.Torrent;
import com.nekoimi.vasashi.mapper.TorrentMapper;
import com.nekoimi.vasashi.service.TorrentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * Torrent Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "torrent-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class TorrentServiceImpl extends ReactiveCrudService<TorrentMapper, Torrent> implements TorrentService {
}
