package com.nekoimi.fastclaw.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nekoimi.fastclaw.mq.MQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * <p>RabbitMQConfiguration</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@EnableRabbit
@Configuration
public class RabbitMQConfiguration {

    @Bean("rabbitMessageQueueTaskExecutor")
    public TaskExecutor rabbitMessageQueueTaskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("rabbit-mq-task-");
        return executor;
    }

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, ObjectMapper objectMapper,
                                                                         @Qualifier("rabbitMessageQueueTaskExecutor") TaskExecutor taskExecutor) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
        factory.setTaskExecutor(taskExecutor);
        factory.setPrefetchCount(4);
        // 消费端初始并发数
        factory.setConcurrentConsumers(10);
        // 消费端最大并发数
        factory.setMaxConcurrentConsumers(10);
        // 应答方式
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    @Bean
    public MessageConverter rabbitMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter(objectMapper);
        return messageConverter;
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(MQConstants.EXCHANGE, true, false);
    }

    @Bean(name = "downloadImageQueue")
    public Queue downloadImageQueue() {
        /**
         * 声明队列的参数
         *
         * boolean durable：是否持久化，代表队列在服务器重启后是否还存在。
         *
         * boolean exclusive：是否排他性队列。排他性队列只能在声明它的Connection中使用，连接断开时自动删除。
         *
         * boolean autoDelete：是否自动删除。如果为true，至少有一个消费者连接到这个队列，之后所有与这个队列连接 的消费者都断开时，队列会自动删除。
         */
        return new Queue(MQConstants.DOWNLOAD_IMAGE_QUEUE, true, false, false);
    }

    @Bean
    public Binding downloadImageBinding(DirectExchange exchange, @Qualifier("downloadImageQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(MQConstants.DOWNLOAD_IMAGE_ROUTE_KEY);
    }

    @Bean(name = "downloadTorrentQueue")
    public Queue downloadTorrentQueue() {
        /**
         * 声明队列的参数
         *
         * boolean durable：是否持久化，代表队列在服务器重启后是否还存在。
         *
         * boolean exclusive：是否排他性队列。排他性队列只能在声明它的Connection中使用，连接断开时自动删除。
         *
         * boolean autoDelete：是否自动删除。如果为true，至少有一个消费者连接到这个队列，之后所有与这个队列连接 的消费者都断开时，队列会自动删除。
         */
        return new Queue(MQConstants.DOWNLOAD_TORRENT_QUEUE, true, false, false);
    }

    @Bean
    public Binding downloadTorrentBinding(DirectExchange exchange, @Qualifier("downloadTorrentQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(MQConstants.DOWNLOAD_TORRENT_ROUTE_KEY);
    }
}
