package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.Torrent;
import com.nekoimi.fastclaw.mapper.TorrentMapper;
import com.nekoimi.fastclaw.service.TorrentService;
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
