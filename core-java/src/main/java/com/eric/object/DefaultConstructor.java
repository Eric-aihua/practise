package com.eric.object;

public class DefaultConstructor {
	public static void main(String args[]){
		OverloadingMethod.method(15, "Eric");
		OverloadingMethod.method("simon", 16);
		
		ConstructorTest1 conTest1=new ConstructorTest1();
		ConstructorTest2 conTest2=new ConstructorTest2();
	}
}

class OverloadingMethod{
	static void method(int age,String name){
		System.out.println("age is:"+age+" name is:"+name);
	}
	static void method(String name,int age){
		System.out.println("name is:"+name+"age is:"+age);
	}
}
class ConstructorTest1{
}	
class ConstructorTest2{
	ConstructorTest2(){
		System.out.println("self defin constructor!");
	}
}
