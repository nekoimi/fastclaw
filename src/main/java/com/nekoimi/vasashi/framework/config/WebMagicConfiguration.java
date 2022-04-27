package com.nekoimi.vasashi.framework.config;

import com.nekoimi.vasashi.framework.config.properties.WebMagicProperties;
import com.nekoimi.vasashi.framework.webmagic.IWebMagicRunner;
import com.nekoimi.vasashi.framework.webmagic.OkHttp3ClientDownloader;
import com.nekoimi.vasashi.framework.webmagic.RedisPoolDuplicateScheduler;
import com.nekoimi.vasashi.webmagic.sehuatang.SeHuaTangRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.Scheduler;

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
        return RedisPoolDuplicateScheduler.of(connectionFactory);
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
    public IWebMagicRunner seHuaTangRunner(WebMagicProperties properties,
                                           Scheduler scheduler,
                                           Downloader downloader,
                                           Pipeline pipeline) {
        return new SeHuaTangRunner(properties.getSite().get("sehuatang"), scheduler, downloader, pipeline);
    }
}
