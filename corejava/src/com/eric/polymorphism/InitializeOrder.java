package com.eric.polymorphism;

public class InitializeOrder{
	private static Temp temp5=new Temp("������ľ�̬������ʼ��");
	private Temp temp6=new Temp("������ķǾ�̬������ʼ��");
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

	private static Temp temp1=new Temp("����ľ�̬������ʼ��");
	private Temp temp2=new Temp("����ķ��o̬������ʼ��");
	public Parent(){
		System.out.println("����Ĺ��캯��");
	}
	static{
		System.out.println("����ľ�̬�����");
	}
	
}
class SunClass extends Parent{
	private static Temp temp3=new Temp("����ľ�̬������ʼ��");
	private Temp temp4=new Temp("����ķǾ�̬������ʼ��");
	public SunClass(){
		System.out.println("����Ĺ��캯��");
	}
	public static void printinformation(){
		System.out.println("����ľ�̬����������");
	}
	public void printinformation(String name){
		System.out.println("����ķǾ�̬����������");
	}
	
	static{
		System.out.println("����ľ�̬�����");
	}
	
}
