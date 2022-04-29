package com.nekoimi.fastclaw.framework.error.exception;

import com.nekoimi.fastclaw.framework.error.Errors;

/**
 * nekoimi  2021/7/20 下午4:44
 */
public class RequestBadCredentialsException extends BaseRuntimeException {
    public RequestBadCredentialsException() {
        super(Errors.HTTP_STATUS_UNAUTHORIZED, "Bad credentials");
    }
}
