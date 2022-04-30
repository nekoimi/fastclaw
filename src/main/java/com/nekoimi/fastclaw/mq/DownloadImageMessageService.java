package com.nekoimi.fastclaw.mq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * <p>DownloadImageMessageService</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@Service
@AllArgsConstructor
public class DownloadImageMessageService {
    private final WebClient webClient;

    @RabbitListener(queues = MQConstants.DOWNLOAD_IMAGE_QUEUE)
    public void download(String payload) {
        log.debug(">>>>>>>>>>>>> {} <<<<<<<<<<<<<", payload);
    }
}
