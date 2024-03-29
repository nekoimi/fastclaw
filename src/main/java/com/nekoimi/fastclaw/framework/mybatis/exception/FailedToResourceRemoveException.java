package com.nekoimi.fastclaw.framework.mybatis.exception;

import com.nekoimi.fastclaw.framework.error.exception.BaseRuntimeException;
import com.nekoimi.fastclaw.framework.mybatis.CrudErrors;
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
