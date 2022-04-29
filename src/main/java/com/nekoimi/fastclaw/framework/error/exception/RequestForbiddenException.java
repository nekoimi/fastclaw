package com.nekoimi.fastclaw.framework.error.exception;

import com.nekoimi.fastclaw.framework.error.Errors;

/**
 * @author Nekoimi  2020/6/8 下午1:59
 */
public class RequestForbiddenException extends BaseRuntimeException {
    public RequestForbiddenException() {
        super(Errors.HTTP_STATUS_FORBIDDEN);
    }
}
