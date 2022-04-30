package com.nekoimi.fastclaw.webmagic.javbooks;

import cn.hutool.core.collection.ListUtil;
import com.nekoimi.fastclaw.framework.webmagic.SiteProperties;
import com.nekoimi.fastclaw.framework.webmagic.processor.IPageProcessor;
import com.nekoimi.fastclaw.framework.webmagic.runner.BaseWebMagicRunner;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.List;

/**
 * <p>JavBooksRunner</p>
 *
 * @author nekoimi 2022/4/30
 */
public class JavBooksRunner extends BaseWebMagicRunner {
    public JavBooksRunner(SiteProperties siteProperties, Scheduler scheduler, Downloader downloader, Pipeline pipeline) {
        super(siteProperties, scheduler, downloader, pipeline);
    }

    @Override
    protected List<String> startUrls() {
        return List.of(host() + "serchinfo_censored/topicsbt/topicsbt_1.htm");
    }

    @Override
    protected List<IPageProcessor> processors() {
        return ListUtil.of(
                new ListPageProcessor(),
                new DetailsPageProcessor()
        );
    }
}
