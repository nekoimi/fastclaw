package com.nekoimi.vasashi.framework.quartz;

import cn.hutool.core.util.ClassUtil;
import com.nekoimi.vasashi.framework.utils.LocalDateTimeUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * <p>QuartzJob</p>
 *
 * @author nekoimi 2022/4/26
 */
public abstract class QuartzJob extends QuartzJobBean implements JobTrigger {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <p>Cron表达式</p>
     *
     * @return
     */
    protected abstract String cronExpression();

    /**
     * <p>执行Job</p>
     *
     * @param dataMap Job上下文数据
     */
    protected abstract void doExecute(JobDataMap dataMap);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.info("QuartzJob[{}] - START {}", getClass(), LocalDateTimeUtils.now());
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            doExecute(dataMap);
            logger.info("QuartzJob[{}] - END {}", getClass(), LocalDateTimeUtils.now());
        } catch (Exception e) {
            logger.info("QuartzJob[{}] - ERROR: {}", getClass(), e.getMessage());
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            logger.error(e.getMessage(), e);
            JobExecutionException jobE = new JobExecutionException(e);
            if (onExceptionRetry()) {
                // Quartz 会立即重新执行该任务, 不断重试，直到成功
                jobE.setRefireImmediately(true);
            } else {
                // Quartz 会自动取消所有与这个 job 有关的 trigger，从而避免再次运行 job
                jobE.setUnscheduleAllTriggers(true);
            }
            throw jobE;
        }
    }

    @Override
    public JobDetail jobDetail() {
        JobBuilder jobBuilder = JobBuilder.newJob();
        jobBuilder.ofType(getClass());
        jobBuilder.withIdentity(jobKey());
        jobBuilder.usingJobData(jobDataMap());
        return jobBuilder.build();
    }

    @Override
    public Trigger trigger() {
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        triggerBuilder.withIdentity(jobKey()).startNow();
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression()));
        return triggerBuilder.build();
    }

    /**
     * <p>遇到异常是否重试</p>
     *
     * @return
     */
    protected boolean onExceptionRetry() {
        return false;
    }

    /**
     * <p>Job Key</p>
     *
     * @return
     */
    protected String jobKey() {
        return ClassUtil.getClassName(getClass(), true);
    }

    /**
     * <p>上下文数据</p>
     *
     * @return
     */
    protected JobDataMap jobDataMap() {
        return new JobDataMap();
    }
}
