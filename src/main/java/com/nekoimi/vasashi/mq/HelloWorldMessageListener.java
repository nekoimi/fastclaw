package com.nekoimi.vasashi.mq;

import com.nekoimi.vasashi.framework.contract.IMessageTopic;
import com.nekoimi.vasashi.framework.listener.AbstractRedisMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>HelloWorldMessageListener</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@Component
public class HelloWorldMessageListener extends AbstractRedisMessageListener<String> {

    @Override
    public IMessageTopic message() {
        return MessageTopic.HELLO_WORLD;
    }

    @Override
    protected void doHandleMessage(String message, String topic) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(">>>>>>>>>>>>> {} <<<<<<<<<<<<<", message);
    }
}
