package com.nekoimi.fastclaw.framework.utils;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.nekoimi.fastclaw.framework.holder.ContextHolder;
import org.springframework.context.ApplicationContext;

/**
 * <p>IdUtils</p>
 *
 * @author nekoimi 2022/4/27
 */
public class IdUtils {

    /**
     * @return
     */
    public static String uuid() {
        ApplicationContext context = ContextHolder.getInstance();
        IdentifierGenerator idGenerator = context.getBean(IdentifierGenerator.class);
        return idGenerator.nextUUID(null);
    }
}
