package com.nekoimi.vasashi.framework.webmagic.processor;

import cn.hutool.core.lang.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

/**
 * <p>DownloadFileProcessor</p>
 *
 * @author nekoimi 2022/4/27
 */
public abstract class BaseDownloadFileProcessor implements IPageProcessor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(PageContext context, Page page) {
        Request request = page.getRequest();
        Dict dict = Dict.create();
        dict.putAll(request.getExtras());
        String filename = dict.getStr("filename");
        byte[] fileBytes = page.getBytes();
        // save
        save(context, filename, fileBytes);
        // Ignore pipeline
        page.setSkip(true);
    }

    /**
     * <p>保存文件</p>
     *
     * @param context
     * @param filename
     * @param fileBytes
     */
    protected abstract void save(PageContext context, String filename, byte[] fileBytes);
}
