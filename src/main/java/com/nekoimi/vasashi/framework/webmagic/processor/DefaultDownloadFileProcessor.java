package com.nekoimi.vasashi.framework.webmagic.processor;

import cn.hutool.core.io.IoUtil;
import us.codecraft.webmagic.Page;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>DownloadFileProcessor</p>
 *
 * @author nekoimi 2022/4/27
 */
public class DefaultDownloadFileProcessor extends BaseDownloadFileProcessor {

    @Override
    public boolean supports(Page page) {
        return false;
    }

    @Override
    protected void save(PageContext context, String filename, byte[] fileBytes) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
             FileOutputStream outputStream = new FileOutputStream(filename)) {
            IoUtil.copy(inputStream, outputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }
}
