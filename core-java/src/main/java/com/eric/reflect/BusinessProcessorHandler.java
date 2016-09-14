package com.eric.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BusinessProcessorHandler implements InvocationHandler {
    private Object tatget;

    public BusinessProcessorHandler(Object tatget) {
        super();
        this.tatget = tatget;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("call " + method.getName() + " before!");
        method.invoke(tatget, args);
        System.out.println("call " + method.getName() + " after!");
        return null;
    }

}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 * 
 */
