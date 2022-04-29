package com.nekoimi.vasashi.framework.config;

import com.nekoimi.vasashi.framework.quartz.JobTrigger;
import com.nekoimi.vasashi.framework.quartz.QuartzJobService;
import com.nekoimi.vasashi.framework.quartz.StdQuartzJobService;
import com.nekoimi.vasashi.framework.runner.QuartzJobRunner;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.Optional;

/**
 * <p>QuartzConfiguration</p>
 *
 * @author nekoimi 2022/4/26
 */
@Slf4j
public class QuartzConfiguration {

    @Bean
    public CommandLineRunner quartzJobRunner() {
        return new QuartzJobRunner();
    }

    @Bean
    public QuartzJobService quartzJobService(Scheduler scheduler,
                                             ObjectProvider<List<JobTrigger>> listObjectProvider) {
        StdQuartzJobService quartzJobService = new StdQuartzJobService(scheduler);
        Optional.ofNullable(listObjectProvider.getIfAvailable())
                .orElse(List.of())
                .forEach(quartzJobService::scheduleJob);
        return quartzJobService;
    }

    /**
     * 消息处理线程池
     *
     * @return
     */
    @Bean(name = "quartzJobThreadPoolTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor quartzJobThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setQueueCapacity(1024);
        taskExecutor.setThreadNamePrefix("quartz-job-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(@Qualifier("quartzJobThreadPoolTaskExecutor") ThreadPoolTaskExecutor taskExecutor) {
        return schedulerFactoryBean -> schedulerFactoryBean.setTaskExecutor(taskExecutor);
    }
}
