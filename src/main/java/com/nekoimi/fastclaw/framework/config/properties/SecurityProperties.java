package com.nekoimi.fastclaw.framework.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * nekoimi  2021/12/16 21:07
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {
    // 不需要认证匹配列表
    private List<String> permitAll = new ArrayList<>();
}
