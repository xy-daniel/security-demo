package com.daniel.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author daniel
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 控制器方法被调用之前
     * @param request  请求
     * @param response  响应
     * @param obj 数据
     * @return 是否拦截
     * @throws Exception 抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.println("TimeInterceptor -----> preHandle");

        System.out.println(((HandlerMethod)obj).getBean().getClass().getName());
        System.out.println(((HandlerMethod)obj).getMethod().getName());

        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * 控制器方法正常执行之后调用
     * @param request  请求
     * @param response 响应
     * @param obj 对象
     * @param mav 数据和视图
     * @throws Exception 抛出异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
        System.out.println("TimeInterceptor -----> postHandle");
        System.out.println("TimeInterceptor耗时:" + (System.currentTimeMillis() - (Long) request.getAttribute("startTime")));
    }

    /**
     * 控制器方法调用之后,返回数据之前
     * @param request 请求
     * @param response 响应
     * @param obj 对象
     * @param e 异常
     * @throws Exception 抛出异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
        System.out.println("TimeInterceptor -----> afterCompletion");
        System.out.println("TimeInterceptor耗时:" + (System.currentTimeMillis() - (Long) request.getAttribute("startTime")));
        //即使抛出异常,如果在ControllerAdvice里面已经处理过了,异常也不会到这儿
        System.out.println("exception is " + (e == null ? "null" : e.getMessage()));
    }
}
