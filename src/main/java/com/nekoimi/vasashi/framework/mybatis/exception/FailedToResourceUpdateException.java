package com.nekoimi.vasashi.framework.mybatis.exception;

import com.nekoimi.vasashi.framework.error.exception.BaseRuntimeException;
import com.nekoimi.vasashi.framework.mybatis.CrudErrors;
/**
 * nekoimi  2021/12/18 17:51
 */
public class FailedToResourceUpdateException extends BaseRuntimeException {
    public FailedToResourceUpdateException() {
        super(CrudErrors.RESOURCE_UPDATE_FAILED);
    }

    public FailedToResourceUpdateException(String message, Object... args) {
        super(CrudErrors.RESOURCE_UPDATE_FAILED, message, args);
    }
}
