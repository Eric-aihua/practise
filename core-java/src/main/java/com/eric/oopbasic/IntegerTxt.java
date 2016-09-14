package com.eric.oopbasic;

public class IntegerTxt {
	public static void main(String[] args) {
		Integer integer=new Integer(10);
		int in=integer.intValue();
		System.out.println(in);
		
		Integer integer2=new Integer(100);
		String s1=integer2.toString();
		String s2=Integer.toString(100, 8);
		System.out.println(s1);
		System.out.println(s2);
		
		String s3="1000";
		int i=Integer.parseInt(s3);
		System.out.println(i);
		
		
	}

}
