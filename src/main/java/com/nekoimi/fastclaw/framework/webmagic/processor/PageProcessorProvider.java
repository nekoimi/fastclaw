package com.nekoimi.fastclaw.framework.webmagic.processor;

import cn.hutool.core.collection.ListUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * <p>PageProcessorProvider</p>
 *
 * @author nekoimi 2022/4/26
 */
@Slf4j
public class PageProcessorProvider implements PageProcessor {
    private final Site site;
    private final PageContext context;
    private final List<IPageProcessor> processors;

    public PageProcessorProvider(PageContext context, Site site, List<IPageProcessor> processors) {
        this.site = site;
        this.context = context;
        this.processors = processors;
    }

    public static PageProcessorProvider of(PageContext context, Site site, IPageProcessor processor) {
        return of(context, site, ListUtil.of(processor));
    }

    public static PageProcessorProvider of(PageContext context, Site site, List<IPageProcessor> processors) {
        return new PageProcessorProvider(context, site, processors);
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    @Override
    public void process(Page page) {
        this.processors.stream()
                .filter(processor -> processor.supports(page))
                .forEach(processor -> {
                    try {
                        processor.process(context, page);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        if (log.isDebugEnabled()) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
