package com.eric.polymorphism;

public class TwoMethod {
	public static void main(String[] args) {
		Parent2 parent=new Sun();
		parent.printInfo();
	}
}

class Parent2 {
	public void printInfo() {
		System.out.println("parent print information");
		this.printName();
	}
	
	public void printName() {
		System.out.println("Eric");
	}
}

class Sun extends Parent2 {
	@Override
	public void printName() {
		System.out.println("Simon");
	}
}

