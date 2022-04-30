package com.nekoimi.fastclaw.webmagic.javbooks;

import cn.hutool.core.util.StrUtil;
import com.nekoimi.fastclaw.framework.webmagic.processor.IPageProcessor;
import com.nekoimi.fastclaw.framework.webmagic.processor.PageContext;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * <p>ListPageProcessor</p>
 *
 * @author nekoimi 2022/4/30
 */
@Slf4j
public class ListPageProcessor implements IPageProcessor {

    @Override
    public boolean supports(Page page) {
        return page.getUrl().regex("serchinfo_censored/topicsbt/topicsbt_[\\d]+\\.htm").match();
    }

    @Override
    public void process(PageContext context, Page page) {
        Html html = page.getHtml();
        List<String> linkList = html.xpath("//*[@id=\"PoShow_Box\"]/div[@class=\"Po_topic\"]/div[@class=\"Po_topic_title\"]/a/@href").all();
        linkList.forEach(page::addTargetRequest);
//        // next
//        String nextUrl = html.xpath("//*[@id=\"PoShow_Box\"]/div[@class=\"PageBar\"]/span[@class=\"pageback\"]/a/@href").get();
//        if (StrUtil.isNotEmpty(nextUrl)) {
//            log.debug("next: {}", nextUrl);
//            page.addTargetRequest(nextUrl);
//        }
    }
}
