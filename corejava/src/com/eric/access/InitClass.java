package com.eric.access;

public class InitClass {
	public static void main(String[] args) {
		System.out.println(Sub.b);
	}
}

class Parent {
	/*
	 * 下面的初始化过程会合并为a=1;a=2,所以子类的b=2
	 */
	static int	a	= 1;
	static {
		a = 2;
	}
}

class Sub extends Parent {
	static int	b	= a;
}
