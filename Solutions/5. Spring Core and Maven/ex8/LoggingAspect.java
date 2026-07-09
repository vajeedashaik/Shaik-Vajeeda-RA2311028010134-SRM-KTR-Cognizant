package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// Exercise 3: Logging with Spring AOP - method execution times
// Exercise 8: Basic AOP - before and after advice
@Aspect
@Component
public class LoggingAspect {

    // Pointcut for all methods in com.library.service package
    @Pointcut("execution(* com.library.service.*.*(..))")
    public void serviceMethods() {}

    // Pointcut for all methods in com.library.repository package
    @Pointcut("execution(* com.library.repository.*.*(..))")
    public void repositoryMethods() {}

    // Exercise 8: Before advice - log before method execution
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[AOP BEFORE] Entering: "
            + joinPoint.getSignature().getDeclaringTypeName()
            + "." + joinPoint.getSignature().getName());
    }

    // Exercise 8: After advice - log after method execution
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AOP AFTER] Exiting: "
            + joinPoint.getSignature().getName());
    }

    // Exercise 3: Around advice - log execution time
    @Around("serviceMethods() || repositoryMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[AOP AROUND] "
            + joinPoint.getSignature().getName()
            + " executed in " + elapsed + " ms");

        return result;
    }

    // After returning - log return value
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AOP AFTER-RETURNING] "
            + joinPoint.getSignature().getName()
            + " returned: " + result);
    }

    // After throwing - log exceptions
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("[AOP AFTER-THROWING] "
            + joinPoint.getSignature().getName()
            + " threw: " + ex.getMessage());
    }
}
