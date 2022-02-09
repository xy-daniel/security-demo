package com.daniel.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 自定义切片
 *
 * 可以获取请求值
 *
 * @author daniel
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.daniel.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        Arrays.stream(args).forEach(System.out::println);
        long startTime = System.currentTimeMillis();
        //方法调用
        Object object = pjp.proceed();
        System.out.println("time aspect:" + (System.currentTimeMillis() - startTime));
        System.out.println("time aspect end");
        return object;
    }

}
