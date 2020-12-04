package com.magnesiatech.springaopexample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ApplicationAspect {

    @Before("execution(* com.magnesiatech.springaopexample.controller.*.*(..))")
    public void beforeController(JoinPoint joinPoint) {
        log.info("received controller call " + joinPoint.toShortString());
    }

/*
    AfterReturning should be used when the method returns without exception
    AfterThrowing should be used when the method throws an exception
    After should be used instead of the previous two and needs to handle both cases.
*/

    @After("execution(* com.magnesiatech.springaopexample.controller.*.*(..))")
    public void afterController(JoinPoint joinPoint) {
        log.info("controller call is ended " + joinPoint.toShortString());
    }

    @AfterReturning("execution(* com.magnesiatech.springaopexample.controller.*.*(..))")
    public void afterReturningController(JoinPoint joinPoint) {
        log.info("controller call result is returned " + joinPoint.toShortString());
    }

    @Around("execution(* com.magnesiatech.springaopexample.service.*.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("starting service call" + joinPoint.toShortString());
        joinPoint.proceed();
        log.info("returning back from service call" + joinPoint.toShortString());
    }



    //the other way of defining the point cut and referencing to it.

    @Around("com.magnesiatech.springaopexample.aspect.CommonJoinPoints.repositoryLayerExecution()")
    public void aroundRepo(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("starting repo call" + joinPoint.getSignature().getDeclaringTypeName());
        joinPoint.proceed();
        log.info("returning back from repo call" + joinPoint.getSignature().getDeclaringTypeName());
    }

    //Aspect by annotation example
    @Around("com.magnesiatech.springaopexample.aspect.CommonJoinPoints.trackTimeAnnotation()")
    public void logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info( "Time taken by {} is {} ",joinPoint, timeTaken);
    }


    @AfterThrowing(value="com.magnesiatech.springaopexample.aspect.CommonJoinPoints.repositoryLayerExecution()",throwing="ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex)
    {
        System.out.println("After Throwing exception in method:"+joinPoint.getSignature());
        System.out.println("Exception is:"+ex.getMessage());
    }

}
