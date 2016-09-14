package com.eric.classinformation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxySample {
	public static void main(String[] args) {
		NormalI proxy = (NormalI) Proxy.newProxyInstance(Normal.class.getClassLoader(), new Class [] { NormalI.class},
		        new NormalHandler(new Normal()));
		proxy.print();
	}
}

interface NormalI {
	void print();
}

class Normal implements NormalI {
	public void print() {
		System.out.println("Normal ouput!");
	}
}

class NormalHandler implements InvocationHandler {
	Normal	normal;
	
	NormalHandler(Normal normal) {
		this.normal = normal;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("NormalHandler beg.....");
		method.invoke(normal, args);
		System.out.println("NormalHandler end.....");
		return null;
	}
	
}
