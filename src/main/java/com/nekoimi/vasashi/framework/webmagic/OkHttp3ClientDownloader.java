package com.nekoimi.vasashi.framework.webmagic;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.Downloader;

import java.util.concurrent.TimeUnit;

/**
 * <p>OkHttpClientDownloader</p>
 *
 * @author nekoimi 2022/4/27
 * @see us.codecraft.webmagic.downloader.HttpClientDownloader
 */
@Slf4j
public class OkHttp3ClientDownloader extends AbstractDownloader {
    private OkHttp3ClientHttpRequestFactory httpRequestFactory;

    public OkHttp3ClientDownloader() {
        createHttpClientFactory(1);
    }

    /**
     * <p>create</p>
     *
     * @return
     */
    public static Downloader of() {
        return new OkHttp3ClientDownloader();
    }

    @Override
    public Page download(Request request, Task task) {
        log.debug("DOWNLOAD: {}", request.getUrl());
        return null;
    }

    @Override
    public void setThread(int threadNum) {
        createHttpClientFactory(threadNum);
    }

    /**
     * <p>createHttpClientFactory</p>
     *
     * @param connectionPoolSize
     */
    private void createHttpClientFactory(int connectionPoolSize) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(connectionPoolSize, connectionPoolSize, TimeUnit.MINUTES))
                .build();
        this.httpRequestFactory = new OkHttp3ClientHttpRequestFactory(httpClient);
    }
}
