package com.nekoimi.vasashi.framework.webmagic;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>PageContext</p>
 *
 * @author nekoimi 2022/4/26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageContext implements Serializable {
    private String host;

    public static PageContext of(String host) {
        return new PageContext(StrUtil.removeSuffix(host, "/") + "/");
    }
}
