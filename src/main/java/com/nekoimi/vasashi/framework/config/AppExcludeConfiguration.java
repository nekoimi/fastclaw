package com.nekoimi.vasashi.framework.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * nekoimi  2021/12/24 15:22
 * <p>
 * 剔除Spring自动配置
 */
@Configuration
@EnableAutoConfiguration(exclude = {
        ReactiveSecurityAutoConfiguration.class,
        QuartzAutoConfiguration.class
})
public class AppExcludeConfiguration {
}
