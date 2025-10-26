package com.java.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class PermissionAspect {

    @Before("execution(* com.java.service.StudentService.*(..))")
    public void checkPermission(JoinPoint joinPoint) {
        System.out.println("权限检查通过");
    }
}
