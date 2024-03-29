package com.nekoimi.fastclaw.framework.config;

import com.nekoimi.fastclaw.framework.config.properties.WebMagicProperties;
import com.nekoimi.fastclaw.framework.webmagic.runner.WebMagicRunner;
import com.nekoimi.fastclaw.framework.webmagic.downloader.OkHttp3ClientDownloader;
import com.nekoimi.fastclaw.webmagic.javbooks.JavBooksRunner;
import com.nekoimi.fastclaw.webmagic.sehuatang.SeHuaTangRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.Map;

/**
 * <p>WebMagicConfiguration</p>
 *
 * @author nekoimi 2022/4/27
 */
@Slf4j
public class WebMagicConfiguration {

    /**
     * <p>Redis实现的去重调度器</p>
     *
     * @param connectionFactory Redis连接池
     * @return
     */
    @Bean
    public Scheduler scheduler(RedisConnectionFactory connectionFactory) {
        // return RedisPoolDuplicateScheduler.of(connectionFactory);
        return new QueueScheduler();
    }

    /**
     * <p>OkHttp3 下载器</p>
     *
     * @return
     */
    @Bean
    public Downloader downloader() {
        return OkHttp3ClientDownloader.of();
    }

    @Bean(name = "seHuaTangRunner")
    public WebMagicRunner seHuaTangRunner(WebMagicProperties properties,
                                          Scheduler scheduler,
                                          Downloader downloader) {
        return new SeHuaTangRunner(properties.getSite().get("sehuatang"), scheduler, downloader, (resultItems, task) -> {
            Map<String, Object> map = resultItems.getAll();
            map.forEach((k, v) -> log.debug("{} -> {}", k, v));
            log.debug("\n");
        });
    }

    @Bean(name = "javBooksRunner")
    public WebMagicRunner javBooksRunner(WebMagicProperties properties,
                                          Scheduler scheduler,
                                          Downloader downloader) {
        return new JavBooksRunner(properties.getSite().get("javbooks"), scheduler, downloader, (resultItems, task) -> {
            Map<String, Object> map = resultItems.getAll();
            map.forEach((k, v) -> log.debug("{} -> {}", k, v));
            log.debug("\n");
        });
    }
}
