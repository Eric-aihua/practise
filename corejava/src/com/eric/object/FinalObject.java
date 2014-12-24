package com.eric.object;

import java.util.Random;

public class FinalObject {
	public static void main(String args[]) {
		FinalClass fc = new FinalClass();
		System.out.println("change before:" + fc);
		/**
		 * final property can't be change
		 * */
		// fc.age=5;
		/**
		 * a is final property,can't to change, but a.name can be change
		 * */
		// fc.a=new A();
		fc.a.name = "simon";
		System.out.println("change after:" + fc);
	}
}

class FinalClass {
	public final A	 a	    = new A();
	/**
	 * final field must to initialize
	 * */
	// public final int height;
	// public final A b;
	public final int	age	= 3;
	
	@Override
	public String toString() {
		return "name:" + a.name + " age:" + age;
	}
}

class FinalDate {
	final String	name;
	final int	 age	= new Random().nextInt();
	
	public FinalDate() {
		// final date must be initialized before to used it!
		name = "Eric";
		System.out.println("age:" + age);
	}
	
	public static void modifyAge(final int age) {
		/**
		 * final argument can't to change
		 * */
		// age++;
	}
}



class A {
	String	name	= "Eric";
	private void a(){
		System.out.println("A.a");
	}
}
class B{
	//@Override
	private void a(){
		System.out.println("B.a");
	}
}