package com.eric.enumtest;

import java.util.Random;

/**
 * 利用多路分发的特性来模拟 石头,见到,布的游戏
 * 
 * @author Eric
 * 
 */
public class RoShamBo1 {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			compete(randomItemByClass(), randomItemByClass());
		}
	}
	
	public static void compete(Item item, Item item2) {
		System.out.println(item + " compete " + item2 + " result is:" + item2.Compete(item));
	}
	
	public static Item randomItemByClass() {
		Random random = new Random();
		int num = random.nextInt(3);
		switch (num) {
		case 0:
			return new Paper();
		case 1:
			return new Rock();
		case 2:
			return new Scissors();
		default:
			return null;
		}
	}
}
