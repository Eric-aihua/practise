package com.eric.enumtest;

public class OverrideEnumMethod {
	public static void main(String[] args) {
		// Direction'toString was Override
		for (Direction direction : Direction.values()) {
			System.out.println(direction);
		}
	}
}