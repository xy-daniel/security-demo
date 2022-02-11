# security-demo
spring-security-demo

### 核心功能

1. 认证(你是谁)
2. 授权(你能干什么)
3. 攻击防护(防止伪造身份)

### 基本原理

security核心其实是一组过滤器链,所有请求都会通过层层过滤才能真正到达真正的api

* UsernamePasswordAuthenticationFilter:可控,表单认证过滤器
* BasicAuthenticationFilter:可控,弹窗认证过滤器
* ExceptionTranslationFilter:不可控,捕获下一个过滤器的异常进行引导
* FilterSecurityInterceptor:不可控,根据配置进行校验,并抛出对应异常


#### 自定义用户认证逻辑

1. 处理用户信息获取逻辑:UserDetailsService
2. 处理用户校验逻辑:UserDetails
3. 处理密码加密解密:PasswordEncoder

