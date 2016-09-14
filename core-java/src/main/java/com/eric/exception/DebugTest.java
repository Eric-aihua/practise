package com.eric.exception;

public class DebugTest {
	public static void main(String[] args) {
		int sum=0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				sum+=i+j;
				System.out.println(sum);
			}
		}
	}
}
