package com.nekoimi.vasashi.framework.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * <p>StdQuartzJobService</p>
 *
 * @author nekoimi 2022/4/26
 */
@Slf4j
public class StdQuartzJobService implements QuartzJobService {
    private final Scheduler scheduler;

    public StdQuartzJobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public Scheduler getScheduler() {
        return this.scheduler;
    }

    @Override
    public void scheduleJob(JobTrigger jobTrigger) {
        try {
            this.scheduler.scheduleJob(jobTrigger.jobDetail(), jobTrigger.trigger());
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }
}
