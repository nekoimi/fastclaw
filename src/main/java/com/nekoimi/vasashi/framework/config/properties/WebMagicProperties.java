package com.nekoimi.vasashi.framework.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>WebMagicProperties</p>
 *
 * @author nekoimi 2022/4/27
 */
@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "webmagic")
public class WebMagicProperties {
    private Map<String, SiteProperties> site = new HashMap<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SiteProperties {
        private String name;
        private String host;
        private Integer poolSize = 1;
    }
}
