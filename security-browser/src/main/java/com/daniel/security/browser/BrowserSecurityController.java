package com.daniel.security.browser;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理登录逻辑
 *
 * @author daniel
 */
@RestController
public class BrowserSecurityController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 缓存当前请求
     */
    private final RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 当需要身份认证时跳转到这里来进行认证
     */
    @RequestMapping("/authentication/require")
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("请求地址:" + redirectUrl);
//            if (StringUtils.endsWithIgnoreCase())
        }
        return null;
    }

}
