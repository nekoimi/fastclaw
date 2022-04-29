package com.nekoimi.fastclaw.framework.webmagic.processor;

import us.codecraft.webmagic.Page;

/**
 * <p>IPageProcessor</p>
 *
 * @author nekoimi 2022/4/26
 */
public interface IPageProcessor {

    /**
     * <p>Support</p>
     *
     * @param page
     * @return
     */
    boolean supports(Page page);

    /**
     * <p>process</p>
     *
     * @param page
     */
    void process(PageContext context, Page page);
}
