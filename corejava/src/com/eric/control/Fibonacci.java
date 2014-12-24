package com.eric.control;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	public static void main(String[] args) {
		System.out.println(fibonacci(10));
	}
	
	static List<Integer> fibonacci(int count) {
		List<Integer> fibList = new ArrayList<Integer>();
		fibList.add(1);
		fibList.add(1);
		for(int i=2;i<count;i++){
			fibList.add(fibList.get(i-2)+fibList.get(i-1));
		}
		return fibList;
	}
}
