package com.eric.polymorphism;

public class PiteFallField {
	public static void main(String[] args) {
		Super sup = new Sub();
		System.out.println(sup.i);
		System.out.println(sup.getI());
		System.out.println(new Sub().getSuper());
		System.out.println(new Sub().i);
		System.out.println(new Sub().getI());
	}
}

class Super {
	int	i	= 0;
	
	public int getI() {
		return i;
	}
	
	public Super() {
		System.out.println("Super init....");
		i = 6;
	}
}

class Sub extends Super {
	int	i	= 1;
	
	public Sub() {
		System.out.println("Sub init....");
	}
	
	public int getI() {
		return i;
	}
	
	public int getSuper() {
		return super.i;
	}
}
