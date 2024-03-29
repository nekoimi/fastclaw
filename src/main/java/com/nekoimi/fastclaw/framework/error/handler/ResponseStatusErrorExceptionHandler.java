package com.nekoimi.fastclaw.framework.error.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.nekoimi.fastclaw.framework.contract.error.ErrorExceptionHandler;
import com.nekoimi.fastclaw.framework.error.ErrorDetails;
import com.nekoimi.fastclaw.framework.error.Errors;
import com.nekoimi.fastclaw.framework.protocol.ErrorDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * nekoimi  2021/7/21 下午3:34
 * <p>
 * HTTP的异常处理
 */
@Slf4j
@Component
public class ResponseStatusErrorExceptionHandler implements ErrorExceptionHandler<ResponseStatusException> {
    @Override
    public Class<ResponseStatusException> getType() {
        return ResponseStatusException.class;
    }

    @Override
    public Mono<? extends ErrorDetails> handle(ServerWebExchange exchange, ResponseStatusException e) {
        HttpStatus status = e.getStatus();
        return Mono.fromCallable(() -> {
            var error = Errors.DEFAULT_SERVER_ERROR;
            if (status.is4xxClientError()) {
                error = buildHttpStatusClientError(status);
            } else if (status.is5xxServerError()) {
                error = buildHttpStatusServerError(status);
            }
            return ErrorDetailsImpl.of(error.code(), error.message(), ExceptionUtil.getRootCauseMessage(e));
        });
    }

    private Errors buildHttpStatusClientError(HttpStatus status) {
        return getClientError(status);
    }

    private Errors buildHttpStatusServerError(HttpStatus status) {
        log.error("Http status server error! {}", status);
        return Errors.DEFAULT_SERVER_ERROR;
    }

    public static Errors getClientError(HttpStatus status) {
        switch (status) {
            case BAD_REQUEST:
                return Errors.HTTP_STATUS_BAD_REQUEST;
            case UNAUTHORIZED:
                return Errors.HTTP_STATUS_UNAUTHORIZED;
            case FORBIDDEN:
                return Errors.HTTP_STATUS_FORBIDDEN;
            case NOT_FOUND:
                return Errors.HTTP_STATUS_NOT_FOUND;
            case METHOD_NOT_ALLOWED:
                return Errors.HTTP_STATUS_METHOD_NOT_ALLOWED;
            case NOT_ACCEPTABLE:
                return Errors.HTTP_STATUS_NOT_ACCEPTABLE;
        }
        return Errors.DEFAULT_CLIENT_ERROR;
    }
}
