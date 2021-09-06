package com.example.aspect.service.basic;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
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


}
