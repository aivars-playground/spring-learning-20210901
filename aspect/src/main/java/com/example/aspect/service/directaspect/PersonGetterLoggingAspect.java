package com.example.aspect.service.directaspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
//will fail if getPerson signature changes
public class PersonGetterLoggingAspect {

    private static Logger LOGGER = Logger.getLogger(PersonGetterLoggingAspect.class.getName());

    @Before("execution(* *.getPerson(..))")
    public void before() {
        LOGGER.info("===========Entering method");
    }

    @After("execution(* *.getPerson(..))")
    public void after() {
        LOGGER.info("===========Exiting method");
    }

    @Around("execution(* *.getPerson(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.toLongString();
        Object args = joinPoint.getArgs();
        LOGGER.info("===========around pre invocation name:"+name +" args"+args);
        Object res = joinPoint.proceed();
        LOGGER.info("===========around post invocation returning:"+res);
        return res;
    }


}
