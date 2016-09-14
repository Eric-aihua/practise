package com.eric.operation;

public class URShift {
	public static void main(String args[]){
		int i1=1;
		int i2=-1;
		System.out.println("i1:"+i1+" binary:"+Integer.toBinaryString(i1)+" not binary:"+Integer.toBinaryString(i1>>>10));
		System.out.println("i2:"+i2+" binary:"+Integer.toBinaryString(i2)+" not binary:"+Integer.toBinaryString(i2>>>10));
		
		char c1=1;
		char c2=(char) -1;
		System.out.println("c1:"+c1+" binary:"+Integer.toBinaryString(c1)+" not binary:"+Integer.toBinaryString(c1>>>10));
		System.out.println("c2:"+c2+" binary:"+Integer.toBinaryString(c2)+" not binary:"+Integer.toBinaryString(c2>>>10));
		
		long l1=1;
		long l2= -1;
		System.out.println("l1:"+l1+" binary:"+Long.toBinaryString(l1)+" not binary:"+Long.toBinaryString(l1>>>10));
		System.out.println("l2:"+l2+" binary:"+Long.toBinaryString(l2)+" not binary:"+Long.toBinaryString(l2>>>10));
	}
}
