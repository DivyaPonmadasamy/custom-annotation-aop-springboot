package com.customannotations.customdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom annotation @DBField to be used in User class
@Target(ElementType.FIELD)  // used over attributes
@Retention(RetentionPolicy.RUNTIME)
public @interface DBField {
    String name();
    Class<?> type();
    boolean isPrimary() default false;
}
