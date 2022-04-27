package com.nekoimi.vasashi.framework.config.properties;

import com.nekoimi.vasashi.framework.webmagic.SiteProperties;
import lombok.Getter;
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
@Setter
@Component
@ConfigurationProperties(prefix = "webmagic")
public class WebMagicProperties {
    private Map<String, SiteProperties> site = new HashMap<>();
}
