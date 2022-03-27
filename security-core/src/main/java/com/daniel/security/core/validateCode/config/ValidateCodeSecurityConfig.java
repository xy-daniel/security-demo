package com.daniel.security.core.validateCode.config;

import com.daniel.security.core.properties.SecurityProperties;
import com.daniel.security.core.validateCode.filter.ValidateCodeFilter;
import com.daniel.security.core.validateCode.validateCodeProcessor.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 验证码校验过滤器配置${@link UsernamePasswordAuthenticationFilter}之前的过滤器
 */
@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationFailureHandler danielAuthenticationFailureHandler;
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.setAuthenticationFailureHandler(danielAuthenticationFailureHandler);
        validateCodeFilter.setValidateCodeProcessorHolder(validateCodeProcessorHolder);
        validateCodeFilter.afterPropertiesSet();

        http
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
