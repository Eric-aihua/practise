package com.eric.operation;

public class AllOpsByType {
	public static void main(String[] args) {
		
	}
	
	public static void booleanTest(boolean b1, boolean b2) {
		// b1=b1+b2;
		// b1=b1-b2;
		// b1=b1*b2;
		// b1=b1/b2;
		//
		// b1++;
		// b1--;
		//
		// b1=+b1;
		// f(b1>b2);
		b1 = b1 && b2;
		b1 = b1 | b2;
		
		// int i1=(int)b1;
	}
	
	public static void intTest(int b1, int b2) {
		b1 = b1 + b2;
		b1 = b1 - b2;
		b1 = b1 * b2;
		b1 = b1 / b2;
		
		b1++;
		b1--;
		
		b1 = +b1;
		f(b1 > b2);
		// b1=b1&&b2;
		b1 = b1 | b2;
		
		int i1 = (int) b1;
	}
	
	public static void charTest(char b1, char b2) {
		// b1 = b1 + b2;
		// b1 = b1 - b2;
		// b1 = b1 * b2;
		// b1 = b1 / b2;
		
		b1++;
		b1--;
		
		// b1 = +b1;
		f(b1 > b2);
		// b1=b1&&b2;
		// b1 = b1 | b2;
		
		int i1 = (int) b1;
	}
	
	public static void longTest(long b1, long b2) {
		b1 = b1 + b2;
		b1 = b1 - b2;
		b1 = b1 * b2;
		b1 = b1 / b2;
		
		b1++;
		b1--;
		
		b1 = +b1;
		f(b1 > b2);
		// b1=b1&&b2;
		b1 = b1 | b2;
		
		int i1 = (int) b1;
	}
	
	public static void doubleTest(double b1, double b2) {
		b1 = b1 + b2;
		b1 = b1 - b2;
		b1 = b1 * b2;
		b1 = b1 / b2;
		
		b1++;
		b1--;
		
		b1 = +b1;
		f(b1 > b2);
		// b1=b1&&b2;
		// b1 = b1 | b2;
		
		int i1 = (int) b1;
	}
	
	public static void byteTest(byte b1, byte b2) {
		// b1 = b1 + b2;
		// b1 = b1 - b2;
		// b1 = b1 * b2;
		// b1 = b1 / b2;
		
		b1++;
		b1--;
		
		// b1 = +b1;
		f(b1 > b2);
		// b1=b1&&b2;
		// b1 = b1 | b2;
		
		int i1 = (int) b1;
	}
	
	public static void shortTest(short b1, short b2) {
		// b1 = b1 + b2;
		// b1 = b1 - b2;
		// b1 = b1 * b2;
		// b1 = b1 / b2;
		
		b1++;
		b1--;
		
		// b1 = +b1;
		f(b1 > b2);
		// b1=b1&&b2;
		// b1 = b1 | b2;
		
		int i1 = (int) b1;
	}
	
	public static void floatTest(float b1, float b2) {
		b1 = b1 + b2;
		b1 = b1 - b2;
		b1 = b1 * b2;
		b1 = b1 / b2;
		
		b1++;
		b1--;
		
		b1 = +b1;
		f(b1 > b2);
		// b1=b1&&b2;
		// b1 = b1 | b2;
		
		int i1 = (int) b1;
	}
	
	static void f(boolean b) {
		System.out.println("f(boolean):" + b);
	}
	
}
