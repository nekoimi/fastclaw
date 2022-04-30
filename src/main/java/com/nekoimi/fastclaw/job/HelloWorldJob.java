package com.nekoimi.fastclaw.job;

import com.nekoimi.fastclaw.mq.MQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>HelloWorldJob2</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@Component
public class HelloWorldJob {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Scheduled(cron = "*/1 * * * * *")
    public void hello2() {
        rabbitTemplate.convertAndSend(MQConstants.DOWNLOAD_IMAGE_ROUTE_KEY, "Hello World, " + System.currentTimeMillis());
    }
}
