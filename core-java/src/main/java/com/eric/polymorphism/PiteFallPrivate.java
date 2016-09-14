package com.eric.polymorphism;

import java.lang.reflect.InvocationTargetException;

public class PiteFallPrivate {
	private void print() {
		System.out.println("PeteFallPrivate.....");
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		PiteFallPrivate pfp = new T();
		System.out.println(pfp.getClass().getName());
		
		pfp.print();
	}
}

class T extends PiteFallPrivate {
	// @Override
	public void print() {
		System.out.println("T method output.....");
	}
}
