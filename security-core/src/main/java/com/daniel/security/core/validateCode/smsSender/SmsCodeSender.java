package com.daniel.security.core.validateCode.smsSender;

/**
 * 短信验证码发送接口
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
