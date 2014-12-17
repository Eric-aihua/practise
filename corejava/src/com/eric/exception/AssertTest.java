package com.eric.exception;

public class AssertTest {
	public static void main(String[] args) {
		int a=2;
		assert a>6:"a<6";
		System.out.println(a);
	}
}
