package com.daniel.security.core.validateCode.config;

import com.daniel.security.core.properties.SecurityProperties;
import com.daniel.security.core.validateCode.generator.ImageCodeGenerator;
import com.daniel.security.core.validateCode.generator.ValidateCodeGenerator;
import com.daniel.security.core.validateCode.smsSender.DefaultSmsCodeSender;
import com.daniel.security.core.validateCode.smsSender.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 找不到已imageCodeGenerator为名的bean时才会使用当前bean
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 找不到已smsCodeSender为名的bean时才会使用当前bean
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
