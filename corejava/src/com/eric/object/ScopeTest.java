package com.eric.object;

public class ScopeTest {
	int x=5;
	public static void main(String[] args){
		int x=6;
		System.out.println(x);
	}
}
