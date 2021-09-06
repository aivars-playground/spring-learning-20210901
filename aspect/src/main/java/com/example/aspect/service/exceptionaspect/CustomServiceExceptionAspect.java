package com.example.aspect.service.exceptionaspect;

import com.example.aspect.service.CustomServiceException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Aspect
@Service
public class CustomServiceExceptionAspect {
    private static Logger LOGGER = Logger.getLogger(CustomServiceExceptionAspect.class.getName());


    @AfterThrowing (pointcut = "execution(* com.example.aspect.service.*.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(CustomServiceException ex)
    {
        LOGGER.severe("====ERROR=============== " + ex);
    }
}
