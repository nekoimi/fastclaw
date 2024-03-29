package com.nekoimi.fastclaw.framework.mybatis.exception;

import com.nekoimi.fastclaw.framework.error.exception.BaseRuntimeException;
import com.nekoimi.fastclaw.framework.mybatis.CrudErrors;
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
