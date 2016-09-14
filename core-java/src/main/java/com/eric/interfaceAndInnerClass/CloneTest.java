package com.eric.interfaceAndInnerClass;

import java.util.Date;
import java.util.GregorianCalendar;

public class CloneTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		Employee e1=new Employee("eric",5000,1986,11,11);
		Employee e2=(Employee)e1.clone();
		System.out.println(e2.getName());
		e2.setName("simon");
		System.out.println(e1.getName());
		System.out.println(e2.getName());
		System.out.println(e2);
		System.out.println(e1);
		System.out.println(e2.getHireDate());
		
		GregorianCalendar gc=new GregorianCalendar(1987,6,15);
		Date d2=gc.getTime();
		e2.setHireDate(d2);
		System.out.println(e2.getHireDate());
	}
}
