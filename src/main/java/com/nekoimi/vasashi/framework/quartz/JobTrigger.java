package com.nekoimi.vasashi.framework.quartz;

import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * <p>JobTrigger</p>
 *
 * @author nekoimi 2022/4/29
 */
public interface JobTrigger {

    /**
     * <p>JobDetail</p>
     *
     * @return
     */
    JobDetail jobDetail();

    /**
     * <p>Trigger</p>
     *
     * @return
     */
    Trigger trigger();
}
