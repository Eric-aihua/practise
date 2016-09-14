package com.eric.enumtest;

import java.util.Random;

import com.eric.generic.Generator;

public class EnumImplementation {
	public static void main(String[] args) {
		// force case SILLY to CartoonCharacter
		CartoonCharacter cc = CartoonCharacter.SILLY;
		for (int i = 0; i < 10; i++) {
			printCartoon(cc);
		}
	}
	
	public static <T> void printCartoon(Generator<T> t) {
		System.out.println(t.next());
	}
}

// enum to implements a interface
enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
	Random	random	= new Random();
	
	public CartoonCharacter next() {
		return values()[random.nextInt(values().length)];
	}
}