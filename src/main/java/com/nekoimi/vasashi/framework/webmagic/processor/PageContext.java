package com.nekoimi.vasashi.framework.webmagic.processor;

import cn.hutool.core.util.StrUtil;
import com.nekoimi.vasashi.framework.webmagic.SiteProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>PageContext</p>
 *
 * @author nekoimi 2022/4/26
 */
@Getter
@Setter
public class PageContext implements Serializable {
    private final String host;
    private final SiteProperties siteProperties;

    public PageContext(String host, SiteProperties siteProperties) {
        this.host = host;
        this.siteProperties = siteProperties;
    }

    public static PageContext of(String host, SiteProperties siteProperties) {
        return new PageContext(StrUtil.removeSuffix(host, "/") + "/", siteProperties);
    }
}
