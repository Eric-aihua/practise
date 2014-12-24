package com.eric.polymorphism;

abstract class TestA{
	public abstract void print();
	public TestA(){
		this.print();
	}
}
public class CallDerivedMethod extends TestA{
	int age=10;
	public void print(){
		System.out.println(age);
	}
	public static void main(String[] args) {
	    TestA test=new CallDerivedMethod();
    }
}
