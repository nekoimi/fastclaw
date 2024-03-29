package com.nekoimi.fastclaw;

import cn.hutool.core.util.StrUtil;
import com.nekoimi.fastclaw.framework.annotations.EnableWebMagic;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.Inet4Address;

@Slf4j
@EnableWebMagic
@EnableScheduling
@SpringBootApplication
public class StartApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(StartApplication.class);
        builder.bannerMode(Banner.Mode.OFF);
        builder.application().setWebApplicationType(WebApplicationType.REACTIVE);
        ConfigurableApplicationContext context = builder.run(args);
        ServerProperties properties = context.getBean(ServerProperties.class);
        String hostAddress = StrUtil.format("{}:{}", Inet4Address.getLocalHost().getHostAddress(), properties.getPort());
        log.info("Listening on http://{}, Swagger url: http://{}/doc.html", hostAddress, hostAddress);
    }
}
