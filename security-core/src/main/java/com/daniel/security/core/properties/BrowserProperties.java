package com.daniel.security.core.properties;

public class BrowserProperties {

    /**
     * 默认登录页
     */
    private String loginPage = "/login.html";

    /**
     * 默认登录类型
     */
    private LoginType loginType = LoginType.JSON;

    /**
     * 默认记住我过期时间
     */
    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
