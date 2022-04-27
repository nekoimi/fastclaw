package com.nekoimi.vasashi.framework.webmagic;

import com.nekoimi.vasashi.framework.config.properties.WebMagicProperties;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>WebMagicRunner</p>
 *
 * @author nekoimi 2022/4/27
 */
public abstract class WebMagicRunner implements IWebMagicRunner {
    protected Spider spider;
    protected WebMagicProperties.SiteProperties siteProperties;
    protected final Scheduler scheduler;
    protected final Downloader downloader;
    protected final Pipeline pipeline;

    public WebMagicRunner(WebMagicProperties.SiteProperties siteProperties,
                          Scheduler scheduler, Downloader downloader, Pipeline pipeline) {
        PageContext context = PageContext.of(host());
        PageProcessorProvider provider = PageProcessorProvider.of(context, site(), processors());
        this.spider = Spider.create(provider)
                .setUUID(name())
                .setScheduler(scheduler)
                .setDownloader(downloader)
                .clearPipeline()
                .addPipeline(pipeline)
                .setExitWhenComplete(true)
                .startUrls(startUrls())
                .thread(threadNum());
        this.siteProperties = siteProperties != null ? siteProperties :
                new WebMagicProperties.SiteProperties("UNKNOW-NAME", "#", 1);
        this.scheduler = scheduler;
        this.downloader = downloader;
        this.pipeline = pipeline;
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
                .setCycleRetryTimes(3);
    }

    protected abstract List<String> startUrls();

    protected abstract List<IPageProcessor> processors();
}
