package com.nekoimi.vasashi.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>HelloWorldMessageListener</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@Component
public class HelloWorldMessageListener {

    @RabbitListener(queues = "test-queue")
    public void doHandleMessage(String payload) {
        log.debug(">>>>>>>>>>>>> {} <<<<<<<<<<<<<", payload);
    }
}
