package com.eric.control;

public class SwitchExec {
	public static void main(String[] args) {
		int c = 'a';
		System.out.println(c);
		
		char i = 97;
		System.out.println(i);
		
		byte b=126;
		int b2=b;
		System.out.println(Byte.MAX_VALUE);
		System.out.println(b2);
		
		float f = 1f;
		// switch integral selector just can be integral
		// switch(f){
		switch ((int) f) {
		case 1:
			System.out.println("a");
			break;
		case 2:
			System.out.println("b");
			break;
		default:
			System.out.println("c");
		}
	}
}
