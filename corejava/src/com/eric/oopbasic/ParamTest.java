package com.eric.oopbasic;

public class ParamTest {
	public static void main(String[] args) {
		int a=10;
		ParamTest pt=new ParamTest();
		pt.change(a);
		System.out.println(a);
		
		Employee e=new Employee();
		e.setSalary(5000);
		pt.change(e);
		System.out.println(e.getSalary());
		
		Employee e1=new Employee();
		e1.setName("eric");
		Employee e2=new Employee();
		e2.setName("simon");
		pt.swap(e1,e2);
		System.out.println(e1.getName());
		System.out.println(e2.getName());
		//pt.change(e1, e2);
		
		Throwable t=new Throwable();
		StackTraceElement[] stes=t.getStackTrace();
		System.out.println("###############################");
		for (StackTraceElement stackTraceElement : stes) {
			System.out.println(stackTraceElement);
		}
		System.out.println("###############################");
	}
	public void change(int x){
		x=2*x;
		System.out.println(x);
	}
	public void change(Employee e){
		e.raiseSalary(10);
		System.out.println(e.getSalary());
	}
	public void change(Employee a,Employee b){
		System.out.println(a.getName());
		System.out.println(b.getName());
		swap(a,b);
		System.out.println(a.getName());
		System.out.println(b.getName());
	}
	public void swap(Employee a,Employee b){
		System.out.println(a+"@@@@@@@@@@@@@@@@@@");
		System.out.println(a.getName());
		System.out.println(b);
		Employee temp=a;
		a=b;
		System.out.println(a+"@@@@@@@@@@@@@@@@@@@@");
		System.out.println(a.getName());
		System.out.println(b);
		b=temp;
		System.out.println(a+"@@@@@@@@@@@@@@@@@@@@");
		System.out.println(a.getName());
		System.out.println(b);
	}
	

}
