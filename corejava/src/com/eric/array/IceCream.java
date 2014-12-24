package com.eric.array;

import java.util.Arrays;
import java.util.Random;

public class IceCream {
	static final String[]	FLAVORS	= { "Chocolate", "Strawberry", "Vanilla Fudge Swirl", "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
	        "Praline Cream", "Mud Pie"};
	
	private static String[] getRandomIce(int count) {
		if (count > FLAVORS.length || count < 0) {
			throw new RuntimeException("count invalid!");
		}
		String[] ices = new String [count];
		boolean[] hasAlready = new boolean [FLAVORS.length];
		Random random = new Random();
		// generate a array, that's not contain repeat element
		for (int i = 0; i < count; i++) {
			
			int rand;
			do {
				rand = random.nextInt(count);
			} while (hasAlready[rand]);
			ices[i] = FLAVORS[rand];
			hasAlready[rand] = true;
		}
		
		return ices;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(Arrays.toString(getRandomIce(5)));
		}
	}
}
