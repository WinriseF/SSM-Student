package com.java.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Order(2)
public class LogAspect {

    @Before("execution(* com.base.service.StudentService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("方法 " + joinPoint.getSignature().getName() + " 开始执行，参数：" +
                Arrays.toString(joinPoint.getArgs()) + "，时间：" + new Date());
    }

    @After("execution(* com.java.service.StudentService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("方法 " + joinPoint.getSignature().getName() + " 执行结束，时间：" + new Date());
    }
}
