package com.eric.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticMethodExecutor {
	
	/**
	 * @param args
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		try {
			Method method = Class.forName("com.eric.reflect.UtilClass").getDeclaredMethod("getName");
			method.setAccessible(true);
			String name = (String) method.invoke(null);
			System.out.println(name);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class UtilClass {
	public static String getName() {
		return "sunaihua";
	}
}
