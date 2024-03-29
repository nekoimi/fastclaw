package com.nekoimi.fastclaw.framework.utils;

import com.nekoimi.fastclaw.framework.error.Errors;
import com.nekoimi.fastclaw.framework.error.exception.BaseRuntimeException;
import com.nekoimi.fastclaw.framework.protocol.ErrorDetailsImpl;
import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;


/**
 * nekoimi  2022/3/14 15:17
 */
public class SendRespUtils {

    /**
     * send json response
     * @param response
     * @param body
     * @return
     */
    public static Mono<Void> sendJson(ServerHttpResponse response, Object body) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(JsonUtils.writeBytes(body));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }

    @SneakyThrows
    public static Mono<Void> sendError(ServerHttpResponse response, Exception e) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ErrorDetailsImpl details = null;
        if (e instanceof BaseRuntimeException) {
            BaseRuntimeException baseRe = (BaseRuntimeException) e;
            details = ErrorDetailsImpl.of(baseRe.getCode(), baseRe.getError().message(), baseRe.getError().trace());
        } else {
            details = ErrorDetailsImpl.of(Errors.DEFAULT_SERVER_ERROR.code(), e.getMessage(), null);
        }

        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(JsonUtils.writeBytes(details));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }
}
