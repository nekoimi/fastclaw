package com.nekoimi.vasashi.job;

import com.nekoimi.vasashi.mq.MessageTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>HelloWorldJob2</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@Component
public class HelloWorldJob {
    private static int num = 0;
    @Autowired
    private ReactiveRedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "*/1 * * * * *")
    public void hello2() {
        if (num <= 10) {
            log.info(">>>>>>>>>>>> Hello World, {}", System.currentTimeMillis());
            redisTemplate.convertAndSend(MessageTopic.HELLO_WORLD.topic(), "Hello World, " + System.currentTimeMillis()).subscribe();
            num++;
        }
    }
}
