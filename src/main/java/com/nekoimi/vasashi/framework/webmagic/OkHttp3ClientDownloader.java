package com.nekoimi.vasashi.framework.webmagic;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.CharsetUtils;

import java.io.IOException;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * <p>OkHttpClientDownloader</p>
 *
 * @author nekoimi 2022/4/27
 * @see us.codecraft.webmagic.downloader.HttpClientDownloader
 */
@Slf4j
public class OkHttp3ClientDownloader extends AbstractDownloader {
    private OkHttpClient httpClient;
    private Proxy proxy = Proxy.NO_PROXY;

    public OkHttp3ClientDownloader() {
        createHttpClient(1);
    }

    public OkHttp3ClientDownloader(Proxy proxy) {
        this.proxy = proxy;
        createHttpClient(1);
    }

    /**
     * <p>create</p>
     *
     * @return
     */
    public static Downloader of() {
        return new OkHttp3ClientDownloader();
    }

    /**
     * <p>create</p>
     *
     * @return
     */
    public static Downloader of(Proxy proxy) {
        return new OkHttp3ClientDownloader(proxy);
    }

    @Override
    public Page download(Request request, Task task) {
        String url = request.getUrl();
        log.debug("download: {}", url);
        Page page = Page.fail();
        HttpMethod httpMethod = HttpMethod.resolve(request.getMethod().toUpperCase());
        if (httpMethod == null) {
            log.error("Http method resolve failed, use {} resolve!", request.getMethod());
            return page;
        }
        try {
            okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder();
            Site taskSite = task.getSite();
            taskSite.getHeaders().forEach(requestBuilder::addHeader);
            if (StrUtil.isNotEmpty(taskSite.getUserAgent())) {
                requestBuilder.addHeader(HttpHeaders.USER_AGENT, taskSite.getUserAgent());
            }
            okhttp3.Request httpRequest = requestBuilder
                    .url(request.getUrl())
                    .method(request.getMethod(), null)
                    .build();
            Response httpResponse = httpClient.newCall(httpRequest).execute();
            page = handleHttpResponse(request, task, httpResponse);
            onSuccess(request);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            onError(request);
        }
        return page;
    }

    @Override
    public void setThread(int threadNum) {
        createHttpClient(threadNum);
    }

    /**
     * <p>createHttpClient</p>
     *
     * @param connectionPoolSize
     */
    private void createHttpClient(int connectionPoolSize) {
        this.httpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(connectionPoolSize, connectionPoolSize, TimeUnit.MINUTES))
                .callTimeout(Duration.ofSeconds(10))
                .connectTimeout(Duration.ofSeconds(10))
                .proxy(proxy)
                .build();
    }

    /**
     * <p>handle http response</p>
     *
     * @param request
     * @param task
     * @param httpResponse
     * @return
     * @throws IOException
     */
    protected Page handleHttpResponse(Request request, Task task, Response httpResponse) throws IOException {
        ResponseBody responseBody = httpResponse.body();
        byte[] bytes = new byte[0];
        if (responseBody != null) {
            bytes = responseBody.bytes();
        }
        Page page = new Page();
        page.setBytes(bytes);
        if (!request.isBinaryContent()) {
            String charset = request.getCharset();
            if (charset == null) {
                charset = task.getSite().getCharset();
                if (charset == null) {
                    String contentType = httpResponse.headers().get(HttpHeaders.CONTENT_TYPE);
                    charset = getHtmlCharset(contentType, bytes);
                }
            }
            page.setCharset(charset);
            page.setRawText(new String(bytes, charset));
        }
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setStatusCode(httpResponse.code());
        page.setDownloadSuccess(true);
        page.setHeaders(httpResponse.headers().toMultimap());
        return page;
    }

    /**
     * <p>getHtmlCharset</p>
     *
     * @param contentType
     * @param contentBytes
     * @return
     * @throws IOException
     */
    private String getHtmlCharset(String contentType, byte[] contentBytes) throws IOException {
        String charset = CharsetUtils.detectCharset(contentType, contentBytes);
        if (charset == null) {
            charset = Charset.defaultCharset().name();
            log.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()", Charset.defaultCharset());
        }
        return charset;
    }
}
