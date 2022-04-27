package com.nekoimi.vasashi.framework.webmagic.pipeline;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * <p>PageModelPipelineAdapter</p>
 *
 * @author nekoimi 2022/4/27
 */
public abstract class PageModelPipelineAdapter<T> implements PageModelPipeline<T> {

    @Override
    public void process(T t, Task task) {

    }
}
