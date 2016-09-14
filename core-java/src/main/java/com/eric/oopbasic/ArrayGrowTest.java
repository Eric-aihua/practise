package com.eric.oopbasic;

import java.lang.reflect.Array;

public class ArrayGrowTest {
	public static void main(String[] args) {
		Employee[] e1=new Employee[10];
		for (int i = 0; i < e1.length; i++) {
			e1[i]=new Employee();
			e1[i].setName("100"+i);
		}
		System.out.println("init finished");
		printArr(e1);
		System.out.println("init print finished");
		Object o=goodGrow(e1);
		System.out.println("grow finished");
		printArr(o);
		System.out.println("print grow finished!");
		
	}
	static Object[] badGrow(Object[] obj){
		int length=(int) (obj.length*1.5);
		Object[] newArr=new Object[length];
		System.arraycopy(obj, 0, newArr, 0, obj.length);
		return newArr;
	}
	static Object goodGrow(Object obj){
		Class c=obj.getClass();
		if(!c.isArray()){
			System.out.println("not array!");
		}
		Class type=c.getComponentType();//获得数组的类型
		int length=Array.getLength(obj);
		int newLen=length*2;
		Object newObj=Array.newInstance(type, newLen);
		System.arraycopy(obj, 0, newObj, 0, length);
		return newObj;
	}
	static void printArr(Object obj){
		Class c=obj.getClass();
		System.out.println(c.getComponentType());
		if(!c.isArray()){
			return;
		}
		for (int i = 0; i < Array.getLength(obj); i++) {
			System.out.println(Array.get(obj, i));
			
		}
	}

}
