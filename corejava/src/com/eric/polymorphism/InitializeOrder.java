package com.eric.polymorphism;

public class InitializeOrder{
	private static Temp temp5=new Temp("调用类的静态变量初始化");
	private Temp temp6=new Temp("调用类的非静态变量初始化");
	public static void main(String args[]){
		SunClass.printinformation();
		//SunClass sunClass=new SunClass();
		//sunClass.printinformation("name");
	}
}

class Temp{
	public Temp(String name){
		System.out.println(name);
	}
}

class Parent{

	private static Temp temp1=new Temp("基类的静态变量初始化");
	private Temp temp2=new Temp("基类的非靜态变量初始化");
	public Parent(){
		System.out.println("基类的构造函数");
	}
	static{
		System.out.println("基类的静态代码块");
	}
	
}
class SunClass extends Parent{
	private static Temp temp3=new Temp("子类的静态变量初始化");
	private Temp temp4=new Temp("子类的非静态变量初始化");
	public SunClass(){
		System.out.println("子类的构造函数");
	}
	public static void printinformation(){
		System.out.println("子类的静态方法被调用");
	}
	public void printinformation(String name){
		System.out.println("子类的非静态方法被调用");
	}
	
	static{
		System.out.println("子类的静态代码块");
	}
	
}
