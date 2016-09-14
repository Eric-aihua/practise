package com.eric.object;

//if primitive data is local data, will compile error
//if primitive data is data of object , it will use default value to initialize it
public class DataObject {
	
	public static void main(String args[]){
		DataHolder dh=new DataHolder();
		System.out.println(dh.name);
		System.out.println(dh.age);
		System.out.println(dh.defaultInt);
	}
	
	public static void printDefaultInt(){
		int i;
		//System.out.println(i);
	}
}
class DataHolder{
	String name="sun ai hua";
	int age=15;
	int defaultInt;
}
