package com.nekoimi.vasashi.service.impl;

import com.nekoimi.vasashi.entity.FileInfo;
import com.nekoimi.vasashi.framework.config.properties.StorageProperties;
import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.framework.web.FileService;
import com.nekoimi.vasashi.mapper.FileInfoMapper;
import com.nekoimi.vasashi.service.FileInfoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;

/**
 * FileInfo Service
 * <p>
 * nekoimi  2022-04-26
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "fileinfo-service", keyGenerator = "cacheKeyGenerator", cacheManager = "redisCacheManager")
public class FileInfoServiceImpl extends ReactiveCrudService<FileInfoMapper, FileInfo> implements FileInfoService {
    private final StorageProperties properties;
    private final FileService fileService;

    @Override
    public Mono<FileInfo> uploadAndSave(FilePart filePart, long contentLength) {
        return fileService.save(filePart, contentLength)
                .map(saveInfo -> saveInfo.convert(FileInfo.class))
                .flatMap(fileInfo -> save(fileInfo).flatMap(this::getById));
    }

    @Override
    public Mono<FileInfo> uploadAndSave(byte[] bytes, String filename, long contentLength) {
        return Mono.fromCallable(() -> DataBufferUtils.readInputStream(() -> new ByteArrayInputStream(bytes), DefaultDataBufferFactory.sharedInstance, bytes.length)
                .map(dataBuffer -> Flux.from(Mono.justOrEmpty(dataBuffer)))
                .flatMap(dataBufferFlux -> fileService.save(dataBufferFlux, filename, contentLength)).last())
                .flatMap(saveInfo -> saveInfo.map(s -> s.convert(FileInfo.class)))
                .flatMap(fileInfo -> save(fileInfo).flatMap(this::getById));
    }

    @Override
    public Mono<Void> show(ServerHttpResponse response, String fileId) {
        return getByIdOrFail(fileId).flatMap(fileInfo -> fileService.show(response, properties.getLocalPath() + fileInfo.getRelativePath()));
    }

    @Override
    public Mono<Void> download(ServerHttpResponse response, String fileId) {
        return getByIdOrFail(fileId).flatMap(fileInfo -> fileService.download(response, fileInfo.getFilename(), properties.getLocalPath() + fileInfo.getRelativePath()));
    }

    @Override
    public Mono<Void> downloadStatic(ServerHttpResponse response, String staticKey) {
        return fileService.downloadStatic(response, staticKey);
    }
}
