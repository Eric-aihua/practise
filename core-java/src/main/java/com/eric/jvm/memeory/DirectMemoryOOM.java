package com.eric.jvm.memeory;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class DirectMemoryOOM {
	private static int	_1M	= 1024 << 10;
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field unsafied = Unsafe.class.getDeclaredFields()[0];
		unsafied.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafied.get(null);
		int count = 0;
		try {
			
			while (true) {
				unsafe.allocateMemory(_1M);
				count++;
			}
		} catch (Throwable th) {
			System.out.println(count);
			th.printStackTrace();
		}
	}
}
