package com.bin.cloud.auth.server.exception;

import javax.naming.AuthenticationException;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/7/10 16:07
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String errorMsg) {
        super(errorMsg);
    }
}
