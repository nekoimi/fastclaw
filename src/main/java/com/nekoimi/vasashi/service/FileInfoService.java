package com.nekoimi.vasashi.service;

import com.nekoimi.vasashi.framework.mybatis.ReactiveICrudService;
import com.nekoimi.vasashi.entity.FileInfo;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.io.InputStream;

/**
 * FileInfo Service
 * <p>
 * nekoimi  2022-04-26
 */
public interface FileInfoService extends ReactiveICrudService<FileInfo> {

    /**
     * <p>上传文件</p>
     *
     * @param filePart      文件
     * @param contentLength 数据长度
     * @return
     */
    Mono<FileInfo> uploadAndSave(FilePart filePart, long contentLength);

    /**
     * <p>上传文件</p>
     *
     * @param bytes         字节数组
     * @param filename      文件名称
     * @param contentLength 数据长度
     * @return
     */
    Mono<FileInfo> uploadAndSave(byte[] bytes, String filename, long contentLength);

    /**
     * <p>显示文件</p>
     *
     * @param response 响应
     * @param fileId   文件ID
     * @return
     */
    Mono<Void> show(ServerHttpResponse response, String fileId);

    /**
     * <p>下载文件</p>
     *
     * @param response 响应
     * @param fileId   文件ID
     * @return
     */
    Mono<Void> download(ServerHttpResponse response, String fileId);

    /**
     * <p>下载静态文件</p>
     *
     * @param response  响应
     * @param staticKey 静态文件Key
     * @return
     */
    Mono<Void> downloadStatic(ServerHttpResponse response, String staticKey);
}
