package com.eric.oopbasic;

import java.util.Random;

public class RandomNumber {
	public static void main() {
		Random random=new Random();
		System.out.println(random.nextInt(100));
		int rNumber=(int)(Math.random()*100);
		System.out.println(rNumber);
	}
	
	

}
