package com.eric.access;

public class CallMethodByReference {
	public static void main(String[] args) {
	    Sun sun=new Sun();
	    sun.say();
    }
}

class Super{
	void print(){
		System.out.println("Super print!");
	}
	void say(){
		print();
		System.out.println("Super say!");
	}
	
}

class Sun extends Super{
	@Override
	void print(){
		System.out.println("Sun print!");
	}
}
