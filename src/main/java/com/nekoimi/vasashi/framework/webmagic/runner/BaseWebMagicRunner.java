package com.nekoimi.vasashi.framework.webmagic.runner;

import com.nekoimi.vasashi.framework.webmagic.SiteProperties;
import com.nekoimi.vasashi.framework.webmagic.processor.PageContext;
import com.nekoimi.vasashi.framework.webmagic.processor.IPageProcessor;
import com.nekoimi.vasashi.framework.webmagic.processor.PageProcessorProvider;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>WebMagicRunner</p>
 *
 * @author nekoimi 2022/4/27
 */
public abstract class BaseWebMagicRunner implements WebMagicRunner {
    protected Spider spider;
    protected SiteProperties siteProperties;
    protected final Scheduler scheduler;
    protected final Downloader downloader;
    protected final Pipeline pipeline;

    public BaseWebMagicRunner(SiteProperties siteProperties,
                              Scheduler scheduler, Downloader downloader, Pipeline pipeline) {
        this.siteProperties = siteProperties != null ? siteProperties : new SiteProperties();
        this.scheduler = scheduler;
        this.downloader = downloader;
        this.pipeline = pipeline;
        this.spider = Spider.create(createPageProcessor())
                .setUUID(name())
                .setScheduler(scheduler)
                .setDownloader(downloader)
                .clearPipeline()
                .addPipeline(pipeline)
                .setExitWhenComplete(true)
                .startUrls(startUrls())
                .thread(threadNum());
    }

    @Override
    public void start() {
        this.spider.run();
    }

    protected String name() {
        return siteProperties.getName();
    }

    protected String host() {
        return siteProperties.getHost();
    }

    protected int threadNum() {
        return siteProperties.getPoolSize();
    }

    protected Site site() {
        return Site.me().setDomain(host())
                .setCharset(StandardCharsets.UTF_8.displayName())
                // 设置重试次数
                .setRetryTimes(3)
                // 设置循环重试次数
                .setCycleRetryTimes(3)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
    }

    protected PageProcessor createPageProcessor() {
        PageContext context = PageContext.of(host(), siteProperties);
        PageProcessorProvider provider = PageProcessorProvider.of(context, site(), processors());
        return provider;
    }

    protected abstract List<String> startUrls();

    protected abstract List<IPageProcessor> processors();
}
