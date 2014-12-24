package com.eric.annotation;

@ExtraInterface(name = "IMultiplier")
public class InterfaceImple {
	public int multiplier(int x, int y) {
		int total = 0;
		for (int i = 0; i < y; i++) {
			total = addNumber(total, x);
		}
		return total;
	}
	
	private int addNumber(int total, int x) {
		return total + x;
		
	}
	
	public static void main(String[] args) {
		System.out.println(new InterfaceImple().multiplier(5, 6));
	}
}
