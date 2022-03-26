package com.daniel.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码校验失败异常
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
