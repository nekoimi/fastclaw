package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.Image;
import com.nekoimi.fastclaw.mapper.ImageMapper;
import com.nekoimi.fastclaw.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * Image Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "image-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class ImageServiceImpl extends ReactiveCrudService<ImageMapper, Image> implements ImageService {
}
