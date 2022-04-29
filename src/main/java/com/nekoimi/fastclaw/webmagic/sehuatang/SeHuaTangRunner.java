package com.nekoimi.fastclaw.webmagic.sehuatang;

import cn.hutool.core.collection.ListUtil;
import com.nekoimi.fastclaw.framework.webmagic.SiteProperties;
import com.nekoimi.fastclaw.framework.webmagic.processor.IPageProcessor;
import com.nekoimi.fastclaw.framework.webmagic.runner.BaseWebMagicRunner;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.List;

/**
 * <p>SeHuaTangRunner</p>
 *
 * @author nekoimi 2022/4/27
 */
public class SeHuaTangRunner extends BaseWebMagicRunner {

    public SeHuaTangRunner(SiteProperties siteProperties,
                           Scheduler scheduler, Downloader downloader, Pipeline pipeline) {
        super(siteProperties, scheduler, downloader, pipeline);
    }

    @Override
    protected List<String> startUrls() {
        return List.of(host() + "forum-103-1.html");
    }

    @Override
    protected List<IPageProcessor> processors() {
        return ListUtil.of(
                new CNListPageProcessor(),
                new CNDetailsPageProcessor()
        );
    }
}
