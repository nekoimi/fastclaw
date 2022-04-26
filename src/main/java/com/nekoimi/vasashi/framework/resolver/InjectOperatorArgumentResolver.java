package com.nekoimi.vasashi.framework.resolver;

import com.nekoimi.vasashi.framework.annotations.InjectOperator;
import com.nekoimi.vasashi.framework.constants.RequestAttributeConstants;

import java.lang.annotation.Annotation;

/**
 * nekoimi  2021/7/2 下午3:11
 */
public class InjectOperatorArgumentResolver extends AbstractInjectRequestAttributeArgumentResolver {

    @Override
    protected String attributeName() {
        return RequestAttributeConstants.REQUEST_USER;
    }

    @Override
    protected Class<? extends Annotation> annotation() {
        return InjectOperator.class;
    }
}
