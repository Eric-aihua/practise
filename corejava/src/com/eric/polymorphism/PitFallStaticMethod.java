package com.eric.polymorphism;

public class PitFallStaticMethod {
	public static void print(){
		System.out.println("base static method output....");
	}
	public void print2(){
		System.out.println("base not static method output...");
	}
	
	public static void main(String[] args) {
		PitFallStaticMethod pfs=new StaticMethodSub();
		pfs.print();
		pfs.print2();
    }
}

class StaticMethodSub extends PitFallStaticMethod{
	public static void print(){
		System.out.println("sub static method output....");
	}
	public void print2(){
		System.out.println("sub not static method output...");
	}
}

