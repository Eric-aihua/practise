package com.eric.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id();

    public String description() default "no description for this method!";
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
