package com.eric.collections;

public class LongEqualTest {
	public static void main(String[] args) {
		Long temp = 1l;
		System.out.print(temp == Long.valueOf("1"));
		System.out.print(temp.equals(Long.valueOf("1")));
	}
}
