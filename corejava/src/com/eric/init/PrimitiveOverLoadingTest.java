package com.eric.init;

public class PrimitiveOverLoadingTest {
	public static void main(String args[]) {
		testConstVal();
		testChar();
		testByte();
		testShort();
		testInt();
		testLong();
		testFloat();
		testDouble();
		
	}
	
	static void f1(char x) {
		System.out.println("f1(char) ");
	}
	
	static void f1(byte x) {
		System.out.println("f1(byte) ");
	}
	
	static void f1(short x) {
		System.out.println("f1(short) ");
	}
	
	static void f1(int x) {
		System.out.println("f1(int) ");
	}
	
	static void f1(long x) {
		System.out.println("f1(long) ");
	}
	
	static void f1(float x) {
		System.out.println("f1(float) ");
	}
	
	static void f1(double x) {
		System.out.println("f1(double) ");
	}
	
	static void f2(byte x) {
		System.out.println("f2(byte) ");
	}
	
	static void f2(short x) {
		System.out.println("f2(short) ");
	}
	
	static void f2(int x) {
		System.out.println("f2(int) ");
	}
	
	static void f2(long x) {
		System.out.println("f2(long) ");
	}
	
	static void f2(float x) {
		System.out.println("f2(float) ");
	}
	
	static void f2(double x) {
		System.out.println("f2(double) ");
	}
	
	static void f3(short x) {
		System.out.println("f3(short) ");
	}
	
	static void f3(int x) {
		System.out.println("f3(int) ");
	}
	
	static void f3(long x) {
		System.out.println("f3(long) ");
	}
	
	static void f3(float x) {
		System.out.println("f3(float) ");
	}
	
	static void f3(double x) {
		System.out.println("f3(double) ");
	}
	
	static void f4(int x) {
		System.out.println("f4(int) ");
	}
	
	static void f4(long x) {
		System.out.println("f4(long) ");
	}
	
	static void f4(float x) {
		System.out.println("f4(float) ");
	}
	
	static void f4(double x) {
		System.out.println("f4(double) ");
	}
	
	static void f5(long x) {
		System.out.println("f5(long) ");
	}
	
	static void f5(float x) {
		System.out.println("f5(float) ");
	}
	
	static void f5(double x) {
		System.out.println("f5(double) ");
	}
	
	static void f6(float x) {
		System.out.println("f6(float) ");
	}
	
	static void f6(double x) {
		System.out.println("f6(double) ");
	}
	
	static void f7(double x) {
		System.out.println("f7(double) ");
	}
	
	static void testConstVal() {
		System.out.println("5: ");
		f1(5);
		f2(5);
		f3(5);
		f4(5);
		f5(5);
		f6(5);
		f7(5);
		System.out.println();
	}
	
	static void testChar() {
		char x = 'x';
		System.out.println("char: ");
		f1(x);
		f2(x);
		f3(x);
		f4(x);
		f5(x);
		f6(x);
		f7(x);
		System.out.println();
	}
	
	static void testByte() {
		byte x = 0;
		System.out.println("byte: ");
		f1(x);
		f2(x);
		f3(x);
		f4(x);
		f5(x);
		f6(x);
		f7(x);
		System.out.println();
	}
	
	static void testShort() {
		short x = 0;
		System.out.println("short: ");
		f1(x);
		f2(x);
		f3(x);
		f4(x);
		f5(x);
		f6(x);
		f7(x);
		System.out.println();
	}
	
	static void testInt() {
		int x = 0;
		System.out.println("int: ");
		f1(x);
		f2(x);
		f3(x);
		f4(x);
		f5(x);
		f6(x);
		f7(x);
		System.out.println();
	}
	
	static void testLong() {
		long x = 0;
		System.out.println("long: ");
		f1(x);
		f2(x);
		f3(x);
		f4(x);
		f5(x);
		f6(x);
		f7(x);
		System.out.println();
	}
	
	static void testFloat() {
		float x = 0;
		System.out.println("float: ");
		f1(x);
		f2(x);
		f3(x);
		f4(x);
		f5(x);
		f6(x);
		f7(x);
		System.out.println();
	}
	
	static void testDouble() {
		double x = 0;
		System.out.println("double: ");
		f1(x);
		f2(x);
		f3(x);
		f4(x);
		f5(x);
		f6(x);
		f7(x);
		System.out.println();
	}
}
