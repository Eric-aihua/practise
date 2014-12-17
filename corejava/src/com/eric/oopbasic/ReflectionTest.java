package com.eric.oopbasic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
	public static void main(String[] args) {
		System.out.println("please enter class name:");
		Scanner sc=new Scanner(System.in);
		String name=sc.nextLine().trim();
		try {
			Class c=Class.forName(name);
			showMessage(c);
		} catch (ClassNotFoundException e) {
			System.out.println("请输入包括包名的类名，或者请检查类名的正确性！");
			//e.printStackTrace();
		}
	}
	public static void showMessage(Class c){
		System.out.println("class "+c.getSimpleName()+"{");
		field(c);
		consrtuctions(c);
		method(c);
	} 
	public static  void consrtuctions(Class c){
		Constructor [] o=c.getConstructors();
		for (Constructor con : o) {
			System.out.print(Modifier.toString(con.getModifiers())+" "+con.getName()+"(");
			Class[] param=con.getParameterTypes();
			for (Class class1 : param) {
				System.out.print(class1.getSimpleName()+" "+class1.getSimpleName()+"1"+",");
			}
			System.out.print(")");
			System.out.println("{}");
		}
		
	}
	public static void method(Class c){
		Method[] method=c.getDeclaredMethods();
		for (Method method2 : method) {
			System.out.print(Modifier.toString(method2.getModifiers())+" "+method2.getReturnType()+" "+method2.getName()+"(");
			Class[] param=method2.getParameterTypes();
			for (Class class1 : param) {
				System.out.println(class1.getSimpleName()+" "+class1.getSimpleName()+"1"+",");
			}
			System.out.print(")");
			System.out.println("{}");
		}
	}
	public static void field(Class c){
		Field[] fileds=c.getDeclaredFields();
		for (Field field : fileds) {
			Class c2=field.getClass();
			System.out.println(Modifier.toString(field.getModifiers())+" "+c2.getSimpleName()+" "+c2.getName()+";");
		}
	}

}
