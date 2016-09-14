package com.eric.operation;

public class BitWise {
	public static void main(String args[]) {
		boolean i = false;
		int h1 = 0x99;
		int h2 = 0x88;
		System.out.println(Integer.toBinaryString(h1));
		System.out.println(Integer.toBinaryString(h2));
		System.out.println(Integer.toBinaryString(h1&h2));
		System.out.println(Integer.toBinaryString(h1|h2));
		System.out.println(Integer.toBinaryString(h1^h2));
		System.out.println(Integer.toBinaryString(~h1));
		//boolean value can't adapter ~
		//System.out.println(~i);
		
	}
}
