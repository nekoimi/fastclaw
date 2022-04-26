package com.nekoimi.vasashi.webmagic.sehuatang;

import cn.hutool.core.util.StrUtil;
import com.nekoimi.vasashi.framework.webmagic.IPageProcessor;
import com.nekoimi.vasashi.framework.webmagic.PageContext;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * <p>CNDetailsPageProcessor</p>
 *
 * @author nekoimi 2022/4/26
 */
@Slf4j
public class CNDetailsPageProcessor implements IPageProcessor {

    @Override
    public boolean supports(Page page) {
        return page.getUrl().regex("thread-[\\d]+-1-1\\.html").match();
    }

    @Override
    public void process(PageContext context, Page page) {
        Html html = page.getHtml();
        String title = html.xpath("//*[@id=\"thread_subject\"]/text()").get();
        String id = html.regex("id=\"postmessage_(\\d+)\"").get();
        List<Selectable> selectableList = html.xpath(StrUtil.format("//*[@id=\"postmessage_{}\"]", id)).nodes();
        selectableList.forEach(selectable -> {
            String performer = removeBlanks(selectable.regex("【出演女优】：(.*?)<br>").get());
            log.debug("title: {}", title);
            log.debug("performer: {}", performer);
        });
    }

    private String removeBlanks(String str) {
        return StrUtil.trim(StrUtil.removeAll(str, "&nbsp;"));
    }
}
