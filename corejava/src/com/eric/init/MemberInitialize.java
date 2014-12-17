package com.eric.init;

public class MemberInitialize {
	public static void main(String[] args) {
		// new OrdeInitialize(4);
		// new OrdeInitialize(4);
		System.out.println("CUPS");
		Cups.cup1.f(11);
		Cups.cup2.f(22);
		
		new Cups2().cup1.f(11);
		new Cups2().cup2.f(22);
	}
}

class Member {
	void localMethod() {
		int local;
		// local++;
	}
}

class Order {
	Order(int num) {
		System.out.println("Order:" + num);
	}
}

class OrdeInitialize {
	int	         i;
	Order	     o1	   = new Order(1);
	static Order	o2	= new Order(2); // just was initialized one time
	                                    
	public OrdeInitialize(int i) {
		this.i = i;
		o3 = new Order(i);
	}
	
	Order	o3	= new Order(3);
}

class Cup {
	Cup(int marker) {
		System.out.println("Cup(" + marker + ")");
	}
	
	void f(int marker) {
		System.out.println("f(" + marker + ")");
	}
}

class Cups {
	static Cup	cup1;
	static Cup	cup2;
	static {
		System.out.println("static area!");
		cup1 = new Cup(11);
		cup2 = new Cup(22);
	}
	
	Cups() {
		System.out.println("Cups()");
	}
}

class Cup2 {
	Cup2(int marker) {
		System.out.println("Cup(" + marker + ")");
	}
	
	void f(int marker) {
		System.out.println("f(" + marker + ")");
	}
}

class Cups2 {
	Cup2	cup1;
	Cup2	cup2;
	{
		System.out.println("non static area!");
		cup1 = new Cup2(1);
		cup2 = new Cup2(2);
	}
	
	Cups2() {
		System.out.println("Cups()");
	}
}
