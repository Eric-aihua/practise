package com.eric.jvm.loading;

public class Initialize {
	private int a;
	private static int staticA;
	private int methodIntialize = initializeClassVariable();
	// private static int staticMethodIntialize=initializeClassVariable();
	private static int staticMethodIntialize = staticInitializeClassVariable();

	public static void main(String args[]) {
		System.out.println(new Initialize().a);
		System.out.println(staticA);
		System.out.println(new Initialize().methodIntialize);
		System.out.println(staticMethodIntialize);

		int b;
		// System.out.println(b);
		Sub.subOutput();
	}

	public int initializeClassVariable() {
		return 1;
	}

	public static int staticInitializeClassVariable() {
		return 1;
	}

	public void printInfo() {
		System.out.println("##############################");
	}

	public String toString() {
		return "***************initialize toString()*******************";
	}

}

class Parent {
	static {
		System.out.println("Parent Class static output.....");
	}

	public static void parentOuput() {
		System.out.println("Parent class output.....");
	}
}

class Sub extends Parent {
	static {
		System.out.println("Sub Class static ouput");
	}

	public static void subOutput() {
		System.out.println("Sub class ouput.....");
	}
}
