package com.eric.operation;

public class Literals {
	public static void main(String[] args) {
		int i1 = 0x2f; // Hexadecimal (lowercase)
		System.out.println("i1: " + Integer.toBinaryString(i1));
		int i2 = 0X2F; // Hexadecimal (uppercase)
		System.out.println("i2: " + Integer.toBinaryString(i2));
		int i3 = 0177; // Octal (leading zero)
		System.out.println("i3: " + Integer.toBinaryString(i3));
		int i4=0xffffffff;
		System.out.println("i4 "+Integer.toBinaryString(i4));
		char c =  0xffff; // max char hex value
		System.out.println("c: " + Integer.toBinaryString(c));
		byte b = 0x7f; // max byte hex value
		System.out.println("b: " + Integer.toBinaryString(b));
		short s = 0x7fff; // max short hex value
		System.out.println("s: " + Integer.toBinaryString(s));
		
		long l1=0xffffffff;
		//long l2=0xffffffff;
		
	}
}