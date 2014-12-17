package com.eric.polymorphism;

class Useful {
	public void f() {
		System.out.println("userful.f");
	}
	
	public void g() {
		System.out.println("userful.g");
	}
}

class MoreUseful extends Useful {
	public void f() {
		System.out.println("moreuseful.f");
	}
	
	public void g() {
		System.out.println("moreuseful.g");
	}
	
	public void u() {
		System.out.println("moreuseful.u");
	}
	
	public void v() {
		System.out.println("moreuseful.v");
	}
	
	public void w() {
		System.out.println("moreuseful.w");
	}
}

public class RTTI {
	public static void main(String[] args) {
		Useful[] x = { new Useful(), new MoreUseful()};
		x[0].f();
		x[0].g();
		
		x[1].f();
		x[1].g();
		
		((MoreUseful)x[1]).v();
		((MoreUseful)x[1]).w();
		
		
	}
}
