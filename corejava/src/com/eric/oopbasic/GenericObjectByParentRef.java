package com.eric.oopbasic;

public class GenericObjectByParentRef {
	public static void main(String args[]){
		Parent obj=new Sun();
		obj.speak();
	}
}

class Sun extends Parent{
	@Override
	public void speak(){
		System.out.println("Sun speak!");
	}
}

class Parent{
	private int age;
	private int height;
	
	public Parent() {
	    super();
    }
	public Parent(int age, int height) {
	    super();
	    this.age = age;
	    this.height = height;
    }
	public int getAge() {
    	return age;
    }
	public void setAge(int age) {
    	this.age = age;
    }
	public int getHeight() {
    	return height;
    }
	public void setHeight(int height) {
    	this.height = height;
    }
	public void speak(){
		System.out.println("Parent speak!");
	}
	
}