package com.eric.oopbasic;

import java.util.ArrayList;
import java.util.Date;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<Employee> staff=new ArrayList<Employee>();
		long s=new Date().getTime();
		//staff.trimToSize();
		for (int i = 0; i < 100000; i++) {
			staff.add(new Employee(i+"00",i));
			System.out.println(i);
		}
		long e=new Date().getTime();
		long spe=e-s;
		System.out.println(spe);
		
		ArrayList sta=new ArrayList();
		sta.add(new Employee());
		sta.add("test");
	}

}
