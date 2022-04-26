package com.nekoimi.vasashi.framework.error.exception.token;

import com.nekoimi.vasashi.framework.error.Errors;
import com.nekoimi.vasashi.framework.error.exception.BaseRuntimeException;
/**
 * @author Nekoimi  2020/5/30 20:35
 */
public class TokenBlackListException extends BaseRuntimeException {
    public TokenBlackListException() {
        super(Errors.TOKEN_BLACK_EXCEPTION);
    }
}
