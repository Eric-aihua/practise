package com.eric.jvm.memeory;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/*
 * 利用CGLib技术不断的生成动态Class,这些Class的信息会被存放在方法区中,如果方法区不是很大会造成方法去的OOM
 * 
 * 
 * -XX:PermSize=10m -XX:MaxPermSize=10m
 * */
public class MethodAreaOOM {
	static class Test {}
	
	public static void main(String[] args) {
		try{
			
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(Test.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object arg0, Method method, Object[] arg2, MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(arg0, arg2);
				}
			});
			System.out.println(enhancer.create());
			
		}
		}catch(Throwable th){
			th.printStackTrace();
		}
	}
}
