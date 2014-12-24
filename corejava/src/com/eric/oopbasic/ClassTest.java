package com.eric.oopbasic;

public class ClassTest {
	public static void main(String[] args) {
		Employee e=new Employee();
		int i=10;
		System.out.println(e.getClass());
		Object[] o=e.getClass().getDeclaredFields();
		for (Object object : o) {
			System.out.println(object.getClass());
		}
		System.out.println(e.getClass());
		System.out.println(Employee.class);
		
	}
}
