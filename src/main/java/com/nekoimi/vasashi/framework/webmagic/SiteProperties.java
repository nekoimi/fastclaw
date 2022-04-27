package com.nekoimi.vasashi.framework.webmagic;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import lombok.*;

/**
 * <p>SiteProperties</p>
 *
 * @author nekoimi 2022/4/27
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SiteProperties {
    private String name;
    private String host;
    private Integer poolSize = 1;
    private Dict extras = Dict.create();

    public void setName(String name) {
        this.name = name;
    }

    public void setHost(String host) {
        this.host = StrUtil.removeSuffix(host, "/") + "/";
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize == null ? 1 : (
                poolSize <= 0 ? 1 : poolSize
        );
    }
}
