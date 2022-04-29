package com.nekoimi.vasashi.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "*/1 * * * * *")
    public void hello2() {
        rabbitTemplate.convertAndSend("test-queue", "Hello World, " + System.currentTimeMillis());
    }
}
