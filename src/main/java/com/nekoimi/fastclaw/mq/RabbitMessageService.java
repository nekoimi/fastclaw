package com.nekoimi.fastclaw.mq;

/**
 * <p>RabbitMessageService</p>
 *
 * @author nekoimi 2022/4/30
 */
public interface RabbitMessageService<M> {

    /**
     * <p>发送消息</p>
     *
     * @param message 消息体
     */
    void send(M message);

    /**
     * <p>处理消息</p>
     *
     * @param message 消息体
     */
    void handleMessage(M message);
}
