package com.eric.init;

public class OverVarargsMethod {
	public static void main(String[] args) {
		// print(1,2,3,4,5);
		
		f(1, 'a');
		//f('a', 'b');
		
	}
	
	static void print(int i, int... a) {
		System.out.println("i" + i);
		for (int j : a) {
			System.out.println(j);
		}
	}
	
	static void print(int... b) {
		for (int i : b) {
			System.out.println(i);
		}
	}
	
	static void f(int i, Character... args) {
		System.out.println("first");
	}
	
	static void f(Character... args) {
		System.out.print("second");
	}
}
