package com.eric.object;

class Insect {
	private static String	name	= printName("Eric");
	
	public Insect() {
		name = "Eric";
		System.out.println("Insect Constructor!");
	}
	
	static String printName(String name) {
		System.out.println("Insect.name=" + name);
		return name = "static " + name;
	}
}

class Bettle extends Insect {
	private static String	subName	= printName("Simon");
	private String	      city	    = printName("SH");
	
	public Bettle() {
		System.out.println("Bettle Constructor!");
	}
	
	public static void printf() {
		System.out.println("Bettle static method!");
	}
//	public static void main(String[] args) {
//		System.out.println("main method.....!");
//		// Bettle bettle=new Bettle();
//		Bettle.printf();// not initialize city field
//	}
}

public class BettleClass {
	public static void main(String[] args) {
		System.out.println("main method.....!");
		Bettle bettle=new Bettle();
		//Bettle.printf();// not initialize city field
	}
}
