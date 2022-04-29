package com.nekoimi.fastclaw.framework.annotations;

import com.nekoimi.fastclaw.framework.config.WebMagicConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * <p>EnableWebMagic</p>
 *
 * @author nekoimi 2022/4/27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Order(Ordered.LOWEST_PRECEDENCE - 100)
@Import(WebMagicConfiguration.class)
public @interface EnableWebMagic {
}
