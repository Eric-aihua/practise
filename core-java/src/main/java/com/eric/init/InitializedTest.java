package com.eric.init;

public class InitializedTest {
	public static void main(String args[]){
		C1 c1=new C1();
		C2 c2=new C2();
		System.out.println(c1.name);
		System.out.println(c2.name);
		
	}
}

class C1{
	 String name="Eric";
	 
	 public String method(String str){
		 return str;
	 }
	 public int method(int i){
		 return i;
	 }
}

class C2{
	 String name;
	public C2(){
		name="Simon";
	}
}
