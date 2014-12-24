package com.eric.extend;

public class Test {
	public static void main(String[] args) {
		Manager m=new Manager(12345,25);
		System.out.println(m.getSalary());
		System.out.println(m.getAge());
		
		Employee[] e=new Employee[5];
		Manager boss=new Manager(12,14);
		System.out.println(boss);
		e[0]=boss;
		System.out.println(e[0]);
		
		Manager[] m2=new Manager[5];
		Employee bo=new Employee(12);
		//m2[0]=(Manager)bo;
		System.out.println(m2[0]);
		
		Employee e3;
		e3=new Manager(13,15);
		System.out.println(e3);
		
	}

}
