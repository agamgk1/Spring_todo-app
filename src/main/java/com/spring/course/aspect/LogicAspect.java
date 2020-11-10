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

//AspectJ - biblioteka/framewoek który pozwala na używanie aspektów w kodzie
//Aspekty pozwalają na wydzielenie pewnych funkcji np. logowanie, tranzakcje, obsługa wyjątkow itp. od kodu biznesowego
//@Component umożliwia rejestrację klasy w springu
//ProcedingJointPoint -punkt łączenia aspektu z logiką @Around
@Aspect
@Component
class LogicAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogicAspect.class);
    // Timer + konstruktor
    private final Timer projectCreateGroupTimer;
    LogicAspect(final MeterRegistry registry) {
        projectCreateGroupTimer = registry.timer("logic.product.create.group");
    }

    //umożliwa wykonanie pewnych zadań przed wywołaniem danej metody (nic nie zwraca (void)). W before uzywany jest JoinPoint zamiast ProceedingJP
    @Before("execution(* com.spring.course.logic.ProjectService.createGroup(..))")
        void logMethodCall(JoinPoint joinPoint) {
            logger.info("Before {} with {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }
    // sposób zapisu wywołania Around danej metody. * ignoruje rodzaj dostępu, .. - ignoruja parametry w metodzie
    //Dookoła wywołania metody create group ma wykonać się kod poniżej adnotacji
    //ProceedingJoinPoint - "zawieszenie się na metodzie, które pozwala ją podmienić" możemy decydować co dalej robić z tą metodą
    @Around("execution(* com.spring.course.logic.ProjectService.createGroup(..))")
    Object aroundProjectCreateGroup(ProceedingJoinPoint jp) {
        //definicja timera czyli określenie co ma byc mierzone.  W tym wypadku jest to metoda createGroup z klasy ProjectService
        return projectCreateGroupTimer.record(() -> {
            // procead - czyli "w tym pukncie ide dalej"
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
