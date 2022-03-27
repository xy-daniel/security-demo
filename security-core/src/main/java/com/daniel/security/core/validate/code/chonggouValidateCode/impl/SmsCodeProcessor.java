package com.daniel.security.core.validate.code.chonggouValidateCode.impl;

import com.daniel.security.core.validate.code.ValidateCode;
import com.daniel.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 发送短信验证码
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {
        //将生成的验证码通过短信的方式发送出去
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }
}
