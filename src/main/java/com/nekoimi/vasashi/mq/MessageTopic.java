package com.nekoimi.vasashi.mq;

import com.nekoimi.vasashi.framework.contract.IMessageTopic;

/**
 * <p>EMessageTopic</p>
 *
 * @author nekoimi 2022/4/29
 */
public enum MessageTopic implements IMessageTopic {
    HELLO_WORLD("HELLO-WORLD");

    private final String name;

    MessageTopic(String name) {
        this.name = name;
    }

    @Override
    public String topic() {
        return name;
    }
}
