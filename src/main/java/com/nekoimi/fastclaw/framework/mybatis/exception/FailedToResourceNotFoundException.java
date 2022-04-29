package com.nekoimi.fastclaw.framework.mybatis.exception;

import com.nekoimi.fastclaw.framework.error.exception.BaseRuntimeException;
import com.nekoimi.fastclaw.framework.mybatis.CrudErrors;

/**
 * nekoimi  2021/12/18 17:51
 */
public class FailedToResourceNotFoundException extends BaseRuntimeException {
    public FailedToResourceNotFoundException() {
        super(CrudErrors.RESOURCE_NOT_FOUND);
    }

    public FailedToResourceNotFoundException(String message, Object... args) {
        super(CrudErrors.RESOURCE_NOT_FOUND, message, args);
    }
}
