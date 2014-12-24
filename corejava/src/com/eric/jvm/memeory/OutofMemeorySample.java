package com.eric.jvm.memeory;

import java.util.ArrayList;
import java.util.List;

public class OutofMemeorySample {
	
	public static void main(String[] args) {
		headOutOfMemory();
	}
	
	/*
	 * -verbose:gc -XX:+PrintGCDetails -verbose:gc
	 * -XX:+HeapDumpOnOutOfMemoryError
	 * 
	 * -Xms20m -Xms20m
	 * 
	 * java -XX:+HeapDumpOnOutOfMemoryError com.eric.jvm.memeory.OutofMemeorySample
	 */
	static void headOutOfMemory() {
		long count = 0;
		try {
			
			List<Object> objects = new ArrayList<Object>();
			while (true) {
				count++;
				objects.add(new Object());
			}
		} catch (Throwable ex) {
			System.out.println(count);
			ex.printStackTrace();
		}
	}
	
}
