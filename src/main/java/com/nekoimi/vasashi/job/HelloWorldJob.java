package com.nekoimi.vasashi.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>HelloWorldJob2</p>
 *
 * @author nekoimi 2022/4/29
 */
@Slf4j
@Component
public class HelloWorldJob {

    @Scheduled(cron = "5 * * * * *")
    public void hello2() {
        log.info(">>>>>>>>>>>> Hello World, {}", System.currentTimeMillis());
    }
}
