package com.eric.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

import com.eric.io.IOOperation;

public class IOEffectiveInterceptor implements InvocationHandler {
	public Object	obj	= null;
	
	public IOEffectiveInterceptor(Object obj) {
		super();
		this.obj = obj;
	}
	
	public static Object newIOInstance(IOOperation operation) {
		return Proxy
		        .newProxyInstance(operation.getClass().getClassLoader(), new Class [] { IOOperation.class}, new IOEffectiveInterceptor(operation));
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("method " + method.getName() + " begin with" + new Date());
		long begin = System.currentTimeMillis();
		Object result = method.invoke(obj, args);
		System.out.print("method " + method.getName() + " end with" + new Date());
		System.out.println("spend time is:" + (System.currentTimeMillis() - begin));
		
		return result;
	}
	
}
