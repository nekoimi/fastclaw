package com.nekoimi.fastclaw.framework.error.exception.token;

import com.nekoimi.fastclaw.framework.error.Errors;
import com.nekoimi.fastclaw.framework.error.exception.BaseRuntimeException;
/**
 * nekoimi  2022/2/16 16:54
 */
public class TokenCannotBeParseException extends BaseRuntimeException {
    public TokenCannotBeParseException() {
        super(Errors.TOKEN_CANNOT_BE_PARSE_EXCEPTION);
    }
}
