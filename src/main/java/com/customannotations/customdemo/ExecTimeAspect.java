package com.customannotations.customdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecTimeAspect {
    // @Pointcut("execution(* com.customannotations.customdemo.*.*(..))") // all methods of all classes within the specified package
    // @Pointcut("execution(* *.get*(..))") // only getters methods of all class from all package
    // @Pointcut("@within(com.customannotations.customdemo.TrackExecutionTime)")  // all methods inside the class of the package that's marked with custom annotation
    @Pointcut("@annotation(com.customannotations.customdemo.TrackExecutionTime)") // only methods that's marked with the custom annotation
    public void execTimeMethods(){} // an alias for the Point Cut to use in ADVICES

    @Before("execTimeMethods()")  // Validation before a method is called
    public void myBeforeAdvice(JoinPoint jp){
        System.out.println("Before execution of " + jp.getSignature().getName()); //returns the name of the method that uses our custom annotation
    }

    @After("execTimeMethods()") // Final clean up (like finally block)
    public void myAfterAdvice(JoinPoint jp){
        System.out.println("After execution of " + jp.getSignature().getName());
    }

    @Around("execTimeMethods()") // Full control on the methods
    public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Around execution of " + pjp.getSignature().getName());
        long start = System.nanoTime();
        Object result = pjp.proceed();  // the method that's executed by this stmt may throw exceptions, so it has to be handled
        long end = System.nanoTime();
        System.out.println("Execution Time: " + (end-start) + " ns");
        return result;
    }

    @AfterReturning(value = "execTimeMethods()", returning = "result")  // after successful execution of the method
    public void myAfterReturn(JoinPoint jp, Object result){
        System.out.println("The method " + jp.getSignature().getName() + " returns: " + result);
    }

    @AfterThrowing(value = "execTimeMethods()", throwing = "error")  // if the method throws an exception
    public void myAfterThrow(JoinPoint jp, Exception error){
        System.out.println("After Throwing Exception in the method: " + jp.getSignature().getName());
        System.out.println("The exception thrown is: " + error.getMessage());
    }
}