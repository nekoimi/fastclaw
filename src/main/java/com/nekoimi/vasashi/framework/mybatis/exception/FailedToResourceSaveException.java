package com.nekoimi.vasashi.framework.mybatis.exception;

import com.nekoimi.vasashi.framework.error.exception.BaseRuntimeException;
import com.nekoimi.vasashi.framework.mybatis.CrudErrors;
/**
 * nekoimi  2021/12/18 17:51
 */
public class FailedToResourceSaveException extends BaseRuntimeException {
    public FailedToResourceSaveException() {
        super(CrudErrors.RESOURCE_SAVING_FAILED);
    }

    public FailedToResourceSaveException(String message, Object... args) {
        super(CrudErrors.RESOURCE_SAVING_FAILED, message, args);
    }
}
