package com.eric.extend;

public class Manager extends Employee {
	private int age;
	public Manager(double sa,int age){
		super(sa);
		this.age=age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public  void ss(){
		System.out.println("manager");
	}

}
