package com.nekoimi.vasashi.webmagic.sehuatang;

import com.nekoimi.vasashi.framework.webmagic.IPageProcessor;
import com.nekoimi.vasashi.framework.webmagic.PageContext;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * <p>CNListPageProcessor</p>
 *
 * @author nekoimi 2022/4/26
 */
@Slf4j
public class CNListPageProcessor implements IPageProcessor {

    @Override
    public boolean supports(Page page) {
        return page.getUrl().regex("forum-103-[\\d]+\\.html").match();
    }

    @Override
    public void process(PageContext context, Page page) {
        Html html = page.getHtml();
        List<String> cssIdList = html.regex("id=\"normalthread_([\\d]+)\"").all();
        cssIdList.stream().map(id -> "normalthread_" + id).forEach(cssId -> {
            String href = html.xpath("//*[@id=\"" + cssId + "\"]/tr/th/a[2]/@href").get();
            log.debug("href: {}", href);
            page.addTargetRequest(context.getHost() + href);
        });
    }
}
