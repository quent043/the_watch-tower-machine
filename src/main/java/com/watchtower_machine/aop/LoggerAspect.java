package com.watchtower_machine.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger("restController");
    private static final Logger LOGGER2 = LoggerFactory.getLogger("STDOUT");

    //TODO: Make it perf reader
    @Around("@annotation(com.watchtower_machine.aop.LoggingAop)")
    public Object logAround (ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Method: {} before execution", joinPoint.getSignature().toString());
        Object result = joinPoint.proceed();
        LOGGER.info("Method: {} after execution", joinPoint.getSignature().toString());
        return result;
    }

    @Before("@annotation(com.watchtower_machine.aop.LoggingAop)")
    public void logBefore (JoinPoint joinpoint) {
        LOGGER2.info("Méthode : {} before execution", joinpoint.getSignature());
    }

}
