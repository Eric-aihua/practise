package com.eric.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

//proxy handler class
class SimpleHandler implements InvocationHandler {

    private Object realObject;
    // measures method-call times
    private static Map<String, Integer> methodCount = new HashMap<String, Integer>();

    public SimpleHandler(Object realObject) {
        this.realObject = realObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // System.out.println("PRE:in SimpleHandler invoke method:" + method.getName());
        if (method.getName().equals("write")) {
            // System.out.println("Write Successfully!");
        }
        Integer count = methodCount.get(method.getName());
        int times = 0;
        if (count == null) {
            methodCount.put(method.getName(), 1);
        } else {
            methodCount.put(method.getName(), ++count);
            times = methodCount.get(method.getName());
        }
        System.out.println(method.getName() + " was excute " + times + " times");

        return method.invoke(realObject, args);
    }

}

public class SimpleDynamicProxy {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    public static void main(String[] args) {
        Do doImpl = (Do) Proxy.newProxyInstance(Do.class.getClassLoader(), new Class[] { Do.class }, new SimpleHandler(
                new DoImpl()));
        doImpl.read();
        doImpl.write();
        doImpl.read();
        doImpl.write();
        doImpl.read();
        doImpl.read();
        doImpl.write();

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
