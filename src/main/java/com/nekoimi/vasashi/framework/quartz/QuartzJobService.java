package com.nekoimi.vasashi.framework.quartz;

import org.quartz.Scheduler;

/**
 * <p>QuartzJobService</p>
 *
 * @author nekoimi 2022/4/26
 */
public interface QuartzJobService {

    /**
     * <p>Scheduler</p>
     *
     * @return
     */
    Scheduler getScheduler();
}
