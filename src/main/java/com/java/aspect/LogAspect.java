package com.java.aspect;

import org.aspectj.lang.JoinPoint;
import java.util.Arrays;
import java.util.Date;

public class LogAspect {
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("方法 " + joinPoint.getSignature().getName() + " 开始执行，参数：" +
                Arrays.toString(joinPoint.getArgs()) + "，时间：" + new Date());
    }
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("方法 " + joinPoint.getSignature().getName() + " 执行结束，时间：" + new Date());
    }
}
