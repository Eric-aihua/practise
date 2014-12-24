package com.eric.object;

public class InitArray {
	public static void main(String args[]) {
		ArrayItem[] arrayItem = new ArrayItem [2];
		for (ArrayItem arrayItem2 : arrayItem) {
			System.out.println(arrayItem2);
		}
		System.out.println();
		Integer[] iA = new Integer [2];
		for (Integer integer : iA) {
			System.out.println(integer);
		}
		int[] intArray = new int [2];
		for (int i : intArray) {
			System.out.println(i);
			
		}
	}
	
	class ArrayItem {
	}
	
}
