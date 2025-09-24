package com.customannotations.customdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

// Configuration is written in config.XML, where each advices is mapped with the methods written in this class
public class ExecTimeAspectXml {
    // Validation before a method is called
    public void myBeforeAdvice(JoinPoint jp){
        System.out.println("Before execution of " + jp.getSignature().getName()); //returns the name of the method that uses our custom annotation
    }

    // Final clean up (like finally block)
    public void myAfterAdvice(JoinPoint jp){
        System.out.println("After execution of " + jp.getSignature().getName());
    }

    // Full control on the methods
    public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Around execution of " + pjp.getSignature().getName());
        long start = System.nanoTime();
        Object result = pjp.proceed();  // the method that's executed by this stmt may throw exceptions, so it has to be handled
        long end = System.nanoTime();
        System.out.println("Execution Time: " + (end-start) + "ns");
        return result;
    }

    // after successful execution of the method
    public void myAfterReturn(JoinPoint jp, Object result){
        System.out.println("The method " + jp.getSignature().getName() + " returns: " + result);
    }

    // if the method throws an exception
    public void myAfterThrow(JoinPoint jp, Exception error){
        System.out.println("After Throwing Exception in the method: " + jp.getSignature().getName());
        System.out.println("The exception thrown is: " + error.getMessage());
    }
}
