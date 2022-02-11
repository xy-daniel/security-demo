package com.daniel.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 浏览器端安全校验配置
 *
 * @author daniel
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HttpSecurity配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        security默认配置
//        http
//                .httpBasic()
//                .and().authorizeRequests().anyRequest().authenticated();

        http
                .formLogin()
                .and().authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 密码加密配置
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
