package com.nekoimi.fastclaw.framework.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.nekoimi.fastclaw.framework.config.properties.StorageProperties;
import com.nekoimi.fastclaw.framework.security.contract.LoginMappingController;
import com.nekoimi.fastclaw.framework.security.contract.LogoutMappingController;
import com.nekoimi.fastclaw.framework.security.filter.RequestDebugLogFilter;
import com.nekoimi.fastclaw.framework.web.FileService;
import com.nekoimi.fastclaw.framework.web.HttpJackson2ObjectMapperBuilderCustomizer;
import com.nekoimi.fastclaw.framework.web.LocalDiskFileService;
import com.nekoimi.fastclaw.framework.web.RewriteResponseBodyResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.WebFilter;

import java.util.Set;

/**
 * nekoimi  2021/7/20 上午9:51
 */
@Slf4j
@Configuration
public class AppConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = LoginMappingController.class, search = SearchStrategy.CURRENT)
    public LoginMappingController SimpleLoginMappingController() {
        return () -> ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login");
    }

    @Bean
    @ConditionalOnMissingBean(value = LogoutMappingController.class, search = SearchStrategy.CURRENT)
    public LogoutMappingController SimpleLogoutMappingController() {
        return () -> ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/logout");
    }

    @Bean
    @Primary
    public PasswordEncoder samplePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnProperty(name = "debug", havingValue = "true")
    public WebFilter requestLogFilter() {
        return new RequestDebugLogFilter();
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public FileService fileService(StorageProperties properties, IdentifierGenerator idGenerator, WebClient webClient) {
        return new LocalDiskFileService(properties, idGenerator, webClient);
    }

    @Bean
    @Primary
    @ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
    public Jackson2ObjectMapperBuilderCustomizer builderCustomizer(ApplicationContext context,
                                                                   JacksonProperties jacksonProperties) {
        return new HttpJackson2ObjectMapperBuilderCustomizer(context, jacksonProperties);
    }

    /**
     * 统一返回值
     *
     * @param configurer
     * @param registry
     * @param resolver
     * @return
     *
     * @see org.springframework.web.reactive.config.WebFluxConfigurationSupport#responseBodyResultHandler(org.springframework.core.ReactiveAdapterRegistry, org.springframework.http.codec.ServerCodecConfigurer, org.springframework.web.reactive.accept.RequestedContentTypeResolver)
     */
    @Bean
    @Primary
    public ResponseBodyResultHandler rewriteResponseBodyResultHandler (ServerCodecConfigurer configurer,
                                                                       @Qualifier("webFluxAdapterRegistry") ReactiveAdapterRegistry registry,
                                                                       @Qualifier("webFluxContentTypeResolver") RequestedContentTypeResolver resolver) {
        RewriteResponseBodyResultHandler resultHandler = new RewriteResponseBodyResultHandler(configurer.getWriters(), resolver, registry);
        resultHandler.setExcludes(Set.of());
        return resultHandler;
    }
}
