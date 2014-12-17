package com.eric.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ExecuteTimerHandler implements InvocationHandler {

    private Object realObject;
    // measures method-call times
    private static Map<String, Integer> methodCount = new HashMap<String, Integer>();

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new ExecuteTimerHandler(obj));
    }

    public ExecuteTimerHandler(Object realObject) {
        this.realObject = realObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // System.out.println("PRE:in SimpleHandler invoke method:" + method.getName());
        
        Integer count = methodCount.get(method.getName());
        int times = 0;
        if (count == null) {
            methodCount.put(method.getName(), 1);
        } else {
            methodCount.put(method.getName(), ++count);
            times = methodCount.get(method.getName());
        }
        //System.out.println(method.getName() + " was excute " + times + " times");
        long begin=System.currentTimeMillis();
        Object obj=method.invoke(realObject, args);
        System.out.println("execute "+method.getName()+" spend "+(System.currentTimeMillis()-begin)+" million sencond!");
        return obj;
    }

}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
