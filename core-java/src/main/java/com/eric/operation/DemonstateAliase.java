package com.eric.operation;

public class DemonstateAliase {
	public static void main(String args[]){
		SimpleObject so1=new SimpleObject();
		so1.f=10f;
		SimpleObject so2=new SimpleObject();
		so2.f=12f;
		System.out.println("original value: so1f:"+so1.f+"   so2.f:"+so2.f);
		so1=so2;
		System.out.println("copy so2 reference into so1:"+so1.f);
		so2.f=15;
		System.out.println("just change so2.f value:"+so1.f);
	}
}
class SimpleObject{
	float f;
}
