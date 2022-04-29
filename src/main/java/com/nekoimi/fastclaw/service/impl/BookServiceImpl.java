package com.nekoimi.fastclaw.service.impl;

import com.nekoimi.fastclaw.framework.mybatis.ReactiveCrudService;
import com.nekoimi.fastclaw.entity.Book;
import com.nekoimi.fastclaw.mapper.BookMapper;
import com.nekoimi.fastclaw.service.BookService;
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
