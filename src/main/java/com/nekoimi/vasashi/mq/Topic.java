package com.nekoimi.vasashi.mq;

import com.nekoimi.vasashi.framework.contract.MessageTopic;

/**
 * <p>EMessageTopic</p>
 *
 * @author nekoimi 2022/4/29
 */
public enum Topic implements MessageTopic {
    HELLO_WORLD("HELLO-WORLD");

    private final String name;

    Topic(String name) {
        this.name = name;
    }

    @Override
    public String topic() {
        return name;
    }
}
