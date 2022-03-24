package com.daniel.security.browser;

import com.daniel.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 浏览器端安全校验配置
 *
 * @author daniel
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler danielAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler danielAuthenticationFailureHandler;

    /**
     * HttpSecurity配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //表单登录
                .formLogin()
                //自定义表单登录页面(缺点是如果不是浏览器访问仍然返回静态登录页面,需要改造)
//                .loginPage("/login.html")
                //是否需要认证时security通过过滤器判断的,如果需要进行身份认证就跳转到配置的路径(无论是静态页面还是控制器)
                .loginPage("/authentication/require")
                //自定义登录请求地址(默认/login)
                .loginProcessingUrl("/authentication/form")
                .successHandler(danielAuthenticationSuccessHandler)
                .failureHandler(danielAuthenticationFailureHandler)
                .and()
                //请求认证
                .authorizeRequests()
                //放通登录页面请求
//                .antMatchers("/login.html").permitAll()
                .antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()
                //其他任何请求需要登录认证
                .anyRequest().authenticated()
                //关闭跨站请求伪造防护
                .and().csrf().disable();
    }

    /**
     * 密码加密配置
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
