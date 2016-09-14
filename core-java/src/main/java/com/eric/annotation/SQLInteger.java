package com.eric.annotation;

public @interface SQLInteger {
    String name() default "";

    Constraints constraints() default @Constraints;
} 

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
