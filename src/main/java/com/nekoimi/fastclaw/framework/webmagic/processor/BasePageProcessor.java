package com.nekoimi.fastclaw.framework.webmagic.processor;

import cn.hutool.core.util.StrUtil;

/**
 * <p>BasePageProcessor</p>
 *
 * @author nekoimi 2022/4/30
 */
public abstract class BasePageProcessor implements IPageProcessor {

    protected String removeBlanks(String str) {
        return StrUtil.trim(
                StrUtil.removeAll(
                        StrUtil.removeAllLineBreaks(str), "&nbsp;"));
    }
}
