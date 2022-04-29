package com.nekoimi.vasashi.framework.runner;

import com.nekoimi.vasashi.framework.holder.ContextHolder;
import org.quartz.Scheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

/**
 * <p>QuartzJobRunner</p>
 *
 * @author nekoimi 2022/4/26
 */
public class QuartzJobRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext context = ContextHolder.getInstance();
        Scheduler scheduler = context.getBean(Scheduler.class);
        scheduler.startDelayed(10);
    }
}
