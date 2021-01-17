package com.spring.course.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
class LogicAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogicAspect.class);
    private final Timer projectCreateGroupTimer;
    LogicAspect(final MeterRegistry registry) {
        projectCreateGroupTimer = registry.timer("logic.product.create.group");
    }
    @Before("execution(* com.spring.course.logic.ProjectService.createGroup(..))")
        void logMethodCall(JoinPoint joinPoint) {
            logger.info("Before {} with {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }
   
    @Around("execution(* com.spring.course.logic.ProjectService.createGroup(..))")
    Object aroundProjectCreateGroup(ProceedingJoinPoint jp) {
        return projectCreateGroupTimer.record(() -> {      
            try {
                return jp.proceed();
            } catch (Throwable e) {
                if (e instanceof RuntimeException) {
                    throw new RuntimeException();
                }
                throw new RuntimeException(e);
            }
        });
    }
}
