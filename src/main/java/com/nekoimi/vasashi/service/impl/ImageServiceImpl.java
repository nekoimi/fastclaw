package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.Image;
import com.nekoimi.vasashi.mapper.ImageMapper;
import com.nekoimi.vasashi.service.ImageService;
import org.springframework.cache.annotation.CacheConfig;
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
