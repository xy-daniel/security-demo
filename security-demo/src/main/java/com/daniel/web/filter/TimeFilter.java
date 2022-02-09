package com.daniel.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义过滤器
 * 未使用注解注入spring,用来模拟三方过滤器的注入
 *
 * @author daniel
 */
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init TimeFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("start TimeFilter");
        long startTime = System.currentTimeMillis();
        //请求下一个过滤器
        chain.doFilter(request, response);
        System.out.println("used time:" + (System.currentTimeMillis() - startTime));
        System.out.println("end TimeFilter");
    }

    @Override
    public void destroy() {
        System.out.println("destroy TimeFilter");
    }
}
