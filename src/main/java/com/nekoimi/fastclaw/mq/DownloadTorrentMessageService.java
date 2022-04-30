package com.nekoimi.fastclaw.mq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * <p>DownloadTorrentMessageService</p>
 *
 * @author nekoimi 2022/4/30
 */
@Slf4j
@AllArgsConstructor
@Component("downloadTorrentMessageService")
public class DownloadTorrentMessageService implements RabbitMessageService<String>{
    private final WebClient webClient;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String message) {
        rabbitTemplate.convertAndSend(MQConstants.DOWNLOAD_TORRENT_ROUTE_KEY, message);
    }

    @Override
    @RabbitListener(queues = MQConstants.DOWNLOAD_TORRENT_QUEUE)
    public void handleMessage(String message) {
        log.debug(">>>>>>>>>>>>> {} <<<<<<<<<<<<<", message);
    }
}
