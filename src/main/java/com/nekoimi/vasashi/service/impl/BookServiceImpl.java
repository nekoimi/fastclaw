package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.entity.Book;
import com.nekoimi.vasashi.mapper.BookMapper;
import com.nekoimi.vasashi.service.BookService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * Book Service
 *
 * nekoimi  2022-04-29
 */
@Service
//@CacheConfig(cacheNames = "book-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class BookServiceImpl extends ReactiveCrudService<BookMapper, Book> implements BookService {
}
