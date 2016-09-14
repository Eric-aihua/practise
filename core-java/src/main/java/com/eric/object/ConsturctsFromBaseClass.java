package com.eric.object;

public class ConsturctsFromBaseClass {
	public static void main(String args[]) {
		DerivedClass dc=new DerivedClass();
		DerivedClass dc2=new DerivedClass("Eric");
		DerivedClass dc3=new DerivedClass(5);
	}
}

class BaseClass {
	public int	i;
	
	public BaseClass() {
		System.out.println("base class no parameter construct!");
	}
	
	public BaseClass(int i) {
		this.i = i;
		System.out.println("base class parameter constructor output!");
	}
}

class DerivedClass extends BaseClass {
	// public DerivedClass(){
	//
	// }
	/**
	 * if don't call super construct from Base class, it's will call default
	 * construct from BaseClass, but in BaseClass have been define parameter
	 * construct, so the complier will complain that is't can't find a construct
	 * of the form BaseClass()
	 **/
	public DerivedClass(int i) {
		super(i);
		System.out.println("Derived output....");
	}
	public DerivedClass(String name){
		System.out.println("parameter is String Derived output.....");
	}
	public DerivedClass(){
		System.out.println("empty parameter derived class constructor output....");
	}
	
	
	
}
