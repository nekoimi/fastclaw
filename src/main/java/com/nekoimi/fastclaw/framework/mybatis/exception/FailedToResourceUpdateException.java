package com.nekoimi.fastclaw.framework.mybatis.exception;

import com.nekoimi.fastclaw.framework.error.exception.BaseRuntimeException;
import com.nekoimi.fastclaw.framework.mybatis.CrudErrors;
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
