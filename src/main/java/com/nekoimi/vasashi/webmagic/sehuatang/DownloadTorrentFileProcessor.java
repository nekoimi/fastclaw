package com.nekoimi.vasashi.webmagic.sehuatang;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.nekoimi.vasashi.framework.webmagic.SiteProperties;
import com.nekoimi.vasashi.framework.webmagic.processor.DownloadFileProcessor;
import com.nekoimi.vasashi.framework.webmagic.processor.PageContext;
import us.codecraft.webmagic.Page;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>DownloadFileProcessor</p>
 *
 * @author nekoimi 2022/4/27
 */
public class DownloadTorrentFileProcessor extends DownloadFileProcessor {

    @Override
    public boolean supports(Page page) {
        return page.getUrl().regex("tupian/down_v[\\d]+\\.php\\?sign=").match();
    }

    @Override
    protected void save(PageContext context, String filename, byte[] fileBytes, Dict extraInfo) {
        SiteProperties siteProperties = context.getSiteProperties();
        String savePath = siteProperties.getExtras().getStr("torrent-file-save-path");
        String movieNumber = extraInfo.getStr("movieNumber");
        String filePath = StrUtil.removeSuffix(savePath, "/") + "/" + movieNumber + ".torrent";
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
             FileOutputStream outputStream = new FileOutputStream(filePath)) {
            IoUtil.copy(inputStream, outputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }
}
