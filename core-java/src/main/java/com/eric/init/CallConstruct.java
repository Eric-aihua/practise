package com.eric.init;

public class CallConstruct {
	public static void main(String[] args) {
	    System.out.println(new ConTest("Eric",25));
    }
}

class ConTest {
	String	name;
	int	   age;
	
	ConTest(String name, int age) {
		this(name);
		System.out.println("ConTest(String name, int age)");
		//this.name=name;
		this.age=age;
	}
	
	ConTest(String name) {
		
		System.out.println("ConTest(String name)");
		this.name=name;
	}
	
	public String toString(){
		return "name"+name+" age"+age;
	}
}
