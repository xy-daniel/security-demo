package com.daniel.security.core.properties;

/**
 * 默认验证码配置
 */
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    /**
     * 允许配置校验验证码的接口
     */
    private String url = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
