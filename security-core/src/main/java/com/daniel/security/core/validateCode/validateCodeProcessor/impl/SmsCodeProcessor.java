package com.daniel.security.core.validateCode.validateCodeProcessor.impl;

import com.daniel.security.core.constant.SecurityConstants;
import com.daniel.security.core.validateCode.sessionStrategyData.ValidateCode;
import com.daniel.security.core.validateCode.exception.ValidateCodeException;
import com.daniel.security.core.validateCode.validateCodeProcessor.ValidateCodeProcessor;
import com.daniel.security.core.validateCode.smsSender.SmsCodeSender;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 发送短信验证码
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {
        //将生成的验证码通过短信的方式发送出去
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        smsCodeSender.send(mobile, smsCode.getCode());
    }

    /**
     * 校验短信验证码
     * @param request
     * @throws Exception
     */
    @Override
    public void validate(ServletWebRequest request) throws Exception {
        ValidateCode codeInSession = (ValidateCode)sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS);

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
    }
}
