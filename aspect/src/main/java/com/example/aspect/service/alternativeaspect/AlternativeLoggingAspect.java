package com.example.aspect.service.alternativeaspect;

import com.example.aspect.service.directaspect.PersonGetterLoggingAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AlternativeLoggingAspect {
    private static Logger LOGGER = Logger.getLogger(AlternativeLoggingAspect.class.getName());

    @Around("@annotation(com.example.aspect.service.alternativeaspect.AlternativeLoggingAnnotation)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.toLongString();
        Object args = joinPoint.getArgs();
        LOGGER.info("=====ALTERNATIVE  pre:"+name +" args"+args);
        Object res = joinPoint.proceed();
        LOGGER.info("=====ALTERNATIVE post invocation returning:"+res);
        return res;
    }
}
