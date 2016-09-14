package com.eric.polymorphism;

public class PolymorphicConstructors {
	public static void main(String[] args) {
		Dog dog = new BigDog();
		dog.printInfo();
	}
}

class Dog {
	private int	height;
	
	public Dog() {
		System.out.println("print before");
		printInfo();
		System.out.println("print after");
	}
	
	public void printInfo() {
		System.out.println("Dog information height:" + height);
	}
	
}

class BigDog extends Dog {
	private int	height	= 1;
	
	public BigDog() {
		height = 5;
	}
	
	public void printInfo() {
		System.out.println("bigDog information height:" + height);
	}
}

/**
 * print before 
 * bigDog information height:0 
 * print after 
 * bigDog information height:5
 */

