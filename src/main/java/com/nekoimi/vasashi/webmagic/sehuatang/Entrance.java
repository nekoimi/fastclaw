package com.nekoimi.vasashi.webmagic.sehuatang;

import cn.hutool.core.collection.ListUtil;
import com.nekoimi.vasashi.framework.webmagic.PageContext;
import com.nekoimi.vasashi.framework.webmagic.PageProcessorProvider;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

/**
 * <p>Entrance</p>
 *
 * @author nekoimi 2022/4/26
 */
public class Entrance {

    public static void main(String[] args) {
        String startPage = "https://xzcfdfsafd.co/forum-103-1.html";
        Site site = Site.me().setCharset("UTF-8");
        PageContext context = PageContext.of("https://xzcfdfsafd.co");
        PageProcessorProvider provider = PageProcessorProvider.of(context, site, ListUtil.of(
                new CNListPageProcessor(),
                new CNDetailsPageProcessor()
        ));
        Spider.create(provider)
                .addUrl(startPage)
                .thread(1)
                .run();
    }
}
