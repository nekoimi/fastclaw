package com.nekoimi.vasashi.framework.mybatis.exception;

import com.nekoimi.vasashi.framework.error.exception.BaseRuntimeException;
import com.nekoimi.vasashi.framework.mybatis.CrudErrors;
/**
 * nekoimi  2021/12/18 17:51
 */
public class FailedToResourceRemoveException extends BaseRuntimeException {
    public FailedToResourceRemoveException() {
        super(CrudErrors.RESOURCE_REMOVE_FAILED);
    }

    public FailedToResourceRemoveException(String message, Object... args) {
        super(CrudErrors.RESOURCE_REMOVE_FAILED, message, args);
    }
}
