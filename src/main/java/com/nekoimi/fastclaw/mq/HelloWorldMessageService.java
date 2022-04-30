package com.nekoimi.fastclaw.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>HelloWorldMessageListener</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@Service
public class HelloWorldMessageService {

    @RabbitListener(queues = "test-queue")
    public void doHandleMessage(String payload) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(">>>>>>>>>>>>> {} <<<<<<<<<<<<<", payload);
    }
}
