package com.eric.enumtest;

public class OzWitch {
	// add a field in Direction and use getInfo to get field
	public static void main(String[] args) {
		for (Direction direction : Direction.values()) {
			direction.printinfo();
			System.out.println(direction.getInfo());
		}
	}
}
