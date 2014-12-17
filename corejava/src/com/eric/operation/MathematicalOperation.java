package com.eric.operation;

import static java.lang.System.out;

import java.util.Random;

public class MathematicalOperation {
	public static void main(String[] args) {
		// Create a seeded random number generator:
		Random rand = new Random(1);
		int i, j, k;
		// Choose value from 1 to 100:
		j = rand.nextInt(100) + 1;
		out.println("j : " + j);
		k = rand.nextInt(100) + 1;
		out.println("k : " + k);
		i = j + k;
		out.println("j + k : " + i);
		i = j - k;
		out.println("j - k : " + i);
		i = k / j;
		out.println("k / j : " + i);
		i = k * j;
		out.println("k * j : " + i);
		i = k % j;
		out.println("k % j : " + i);
		j %= k;
		out.println("j %= k : " + j);
		// Floating-point number tests:
		float u, v, w; // Applies to doubles, too
		v = rand.nextFloat();
		out.println("v : " + v);
		w = rand.nextFloat();
		out.println("w : " + w);
		v = 0.7f;
		w = 0.3f;
		u = v + w;
		out.println("v + w : " + u);
		u = v - w;
		out.println("v - w : " + u);
		u = v * w;
		out.println("v * w : " + u);
		u = v / w;
		out.println("v / w : " + u);
		
		u = v % w;
		out.println("v % w : " + u);
		
	}
}
