package com.eric.operation;

public class CompareObject {
	public static void main(String[] args) {
	    Dog dog1=new Dog();
	    dog1.name="huahua";
	    dog1.says="wangwang";
	    
	    Dog dog2=new Dog();
	    dog2.name="WC";
	    dog2.says="lakd";
	    
	    Dog dog3=new Dog();
	    dog2=dog3;
	    System.out.println(dog2==dog3);
	    System.out.println(dog2.equals(dog3));
    }
}
class Dog{
	String name;
	String says;
}
