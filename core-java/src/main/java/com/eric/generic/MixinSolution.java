package com.eric.generic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MixinSolution {
	public static void main(String[] args) {
		/*
		 * use interface to produce effect of mixing, to make EleWatch have
		 * Watch,Clock Calendar functions
		 */
		EleWatch ew = new EleWatch();
		ew.calendar();
		ew.clock();
		ew.printTime();
		/*
		 * use dynamic proxy to produce effect of mixing
		 */
		Object mixing = MixinHandler.newInstance(new Tuple2(new ClockImp(), Clock.class), new Tuple2(new CalendarImp(), Calendar.class));
		Clock clock = (Clock) mixing;
		clock.clock();
		Calendar calendar = (Calendar) mixing;
		calendar.calendar();
	}
}

// ............................................dynamic proxy
class MixinHandler implements InvocationHandler {
	private static Map<String, Object>	delegateMethod	= new HashMap<String, Object>();
	
	private MixinHandler(Tuple2<Object, Class<?>>... paris) {
		
	}
	
	private static void initDelegateMethods(Tuple2<Object, Class<?>>... paris) {
		for (Tuple2<Object, Class<?>> tuple2 : paris) {
			for (Method method : tuple2.b.getMethods()) {
				String methodName = method.getName();
				if (!delegateMethod.containsKey(methodName)) {
					// put method name as key, Object as value in deledateMethod
					// map
					delegateMethod.put(methodName, tuple2.a);
				}
			}
		}
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before invoke");
		String methodName = method.getName();
		Object delegate = delegateMethod.get(methodName);
		if (delegate != null) {
			return method.invoke(delegate, args);
		} else {
			throw new RuntimeException("method not found!");
		}
	}
	
	public static Object newInstance(Tuple2<Object, Class<?>>... pairs) {
		initDelegateMethods(pairs);
		Class<?>[] interfaces = new Class [pairs.length];
		int count = 0;
		for (Tuple2<Object, Class<?>> tuple2 : pairs) {
			interfaces[count++] = tuple2.b;
		}
		return Proxy.newProxyInstance(interfaces[0].getClassLoader(), interfaces, new MixinHandler());
	}
	
}

// ............................................interface

interface Clock {
	public void clock();
}

class ClockImp implements Clock {
	public void clock() {
		System.out.println("Clocking....");
	}
}

interface Calendar {
	public void calendar();
}

class CalendarImp implements Calendar {
	public void calendar() {
		System.out.println("Calending....");
	}
}

interface Timer {
	public void printTime();
}

class Watch implements Timer {
	public void printTime() {
		System.out.println(new Date());
	}
}

/*
 * use interface to produce effect of mixing, to make EleWatch have Watch,Clock
 * Calendar functions
 */
class EleWatch extends Watch implements Clock, Calendar {
	private Clock	 clock	     = new ClockImp();
	private Calendar	calendar	= new CalendarImp();
	
	public void calendar() {
		calendar.calendar();
	}
	
	public void clock() {
		clock.clock();
		
	}
	
}
