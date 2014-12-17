package com.eric.exception;

public class JconsoleTest {
	public static void main(String[] args) {
		int a=1;
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 10000; j++) {
				a=a+i;
				a=a+j;
				test t=new test();
				t=null;
				System.out.println(t);
				System.out.println(a);
			}
		}
	}
}
class test{
	
}