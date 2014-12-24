package com.eric.operation;

public class CommaOperation {
	public static void main(String[] args) {
		for (int i = 1, j = i + 10; printInit(i); i++,printCondition(i)) {
			System.out.println("i = " + i + " j = " + j);
		}
	}
	
	static boolean printInit(int i){
		System.out.println("init");
		return i<5;
	}
	static void printCondition(int i){
		System.out.println("condition");
	}
	static void printStep(int i){
		System.out.println("step");
	}
}
