package com.customannotations.customdemo;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation @TrackExecutionTime to be used in User class to track the execution time of the methods that's been annotated
@Target(ElementType.METHOD) // used over methods, can also be TYPE (class) or FIELD (attributes)
@Retention(RetentionPolicy.RUNTIME) //can also be CLASS (compile-time) or SOURCE (for source code)
public @interface TrackExecutionTime {  //marker annotation (custom) - no logic needed in this case
    //String value() default "";
}