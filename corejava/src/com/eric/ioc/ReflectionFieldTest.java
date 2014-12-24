package com.eric.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionFieldTest {
	private String str1;
	private String str2;
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public void sayHello(String name){
		System.out.println("hello:"+name);
	}
	public ReflectionFieldTest(String str1, String str2) {
		super();
		this.str1 = str1;
		this.str2 = str2;
	}
	public static void main(String[] args) {
		try {
		
			Object[] obj={"a","b"};
			Object[] pa={"eric"};
			Class[] cons={String.class,String.class};
			Class[] param={String.class};
			Constructor con=ReflectionFieldTest.class.getConstructor(cons);
			ReflectionFieldTest rft=(ReflectionFieldTest)con.newInstance(obj);
			Field[] fs=rft.getClass().getDeclaredFields();
			Method method=rft.getClass().getDeclaredMethod("sayHello",param);
//			Field[] fs=rft.getClass().getFields();
			System.out.println(fs.length);
			int i=0;
			for (Field field : fs) {
				System.out.println(field.get(rft));
				field.set(rft, "XXX"+i);
				++i;
				System.out.println(field.get(rft));
			}
			Object o=method.invoke(rft,pa);//调用方法
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
