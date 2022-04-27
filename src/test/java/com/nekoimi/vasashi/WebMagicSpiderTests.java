package com.nekoimi.vasashi;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.nekoimi.vasashi.framework.webmagic.OkHttp3ClientDownloader;
import com.nekoimi.vasashi.framework.webmagic.PageContext;
import com.nekoimi.vasashi.framework.webmagic.PageProcessorProvider;
import com.nekoimi.vasashi.framework.webmagic.RedisPoolDuplicateScheduler;
import com.nekoimi.vasashi.webmagic.sehuatang.CNDetailsPageProcessor;
import com.nekoimi.vasashi.webmagic.sehuatang.CNListPageProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * <p>WebMagicSpiderTests</p>
 *
 * @author nekoimi 2022/4/27
 */
@SpringBootTest
public class WebMagicSpiderTests {
    @Autowired
    private IdentifierGenerator idGenerator;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    void runTest1() {
        Scheduler scheduler = RedisPoolDuplicateScheduler.of(redisConnectionFactory);
        Downloader downloader = OkHttp3ClientDownloader.of();
        String startPage = "https://xzcfdfsafd.co/forum-103-1.html";
        Site site = Site.me().setCharset("UTF-8");
        PageContext context = PageContext.of("https://xzcfdfsafd.co");
        PageProcessorProvider provider = PageProcessorProvider.of(context, site, ListUtil.of(
                new CNListPageProcessor(),
                new CNDetailsPageProcessor()
        ));
        String uuid = idGenerator.nextUUID(null);
        Spider.create(provider)
                .setUUID(uuid)
                .setScheduler(scheduler)
                .setDownloader(downloader)
                .thread(1)
                .addUrl(startPage)
                .run();
    }
}
