package com.example.management.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * 服务层方法切面
 * 监控性能
 */
@Component
@Aspect
public class ServiceAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.example.management.service..*)")
    public void ServiceAspect() {
    }

    @Around("ServiceAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        logger.info("服务层方法:{}--执行时间:{}毫秒", joinPoint.getSignature(), System.currentTimeMillis() - startTime);
        return result;
    }
}
