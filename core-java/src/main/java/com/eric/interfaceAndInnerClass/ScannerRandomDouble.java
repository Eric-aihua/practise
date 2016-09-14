package com.eric.interfaceAndInnerClass;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class ScannerRandomDouble {
	public static void main(String[] args) {
		Scanner scan = new Scanner(new RandomDouble(10));
		while (scan.hasNext()) {
			System.out.println(scan.next());
		}
	}
}

class RandomDouble implements Readable {
	private static Random	    rand	 = new Random(47);
	private static final char[]	capitals	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[]	lowers	 = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final char[]	vowels	 = "aeiou".toCharArray();
	private int	                count;
	
	public RandomDouble(int count) {
		this.count = count;
	}
	
	public int read(CharBuffer cb) throws IOException {
		if (count-- <= 0)
			return -1;
		for (int i = 0; i < 4; i++) {
			cb.append(capitals[rand.nextInt(capitals.length)]);
			cb.append(lowers[rand.nextInt(lowers.length)]);
		}
		cb.append(vowels[rand.nextInt(vowels.length)]);
		cb.append('\n');
		return 9;
	}
	
}
