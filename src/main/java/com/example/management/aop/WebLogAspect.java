package com.example.management.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * 日志切面
 */
@Component
@Aspect
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.example.management.app.controller..*)")
    public void webLogAspect() {
    }

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLogAspect()")
    public void doBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLogAspect()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}
