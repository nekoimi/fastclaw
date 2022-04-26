package com.nekoimi.vasashi.framework.contract.error;

import com.nekoimi.vasashi.framework.error.ErrorDetails;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * nekoimi  2021/12/13 22:12
 *
 * 异常消息结果处理
 */
public interface ErrorExceptionResultWriter {

    /**
     * @param exchange
     * @param error
     * @return
     */
    Mono<Void> writer(ServerWebExchange exchange, ErrorDetails error);
}
