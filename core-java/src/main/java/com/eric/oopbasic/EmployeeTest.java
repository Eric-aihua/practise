package com.eric.oopbasic;

import java.util.Date;
import java.util.GregorianCalendar;

public class EmployeeTest {
	public static void main(String[] args) {
		Employee simon=new Employee("simon",71000,2004,11,17);
		Employee jack=new Employee("jack",48400,2004,12,17);
		Employee eric=new Employee("eric",61000,2005,11,17);
		
		Employee[] ea=new Employee[3];
		ea[0]=simon;
		ea[1]=jack;
		ea[2]=eric;
		for (int i = 0; i < ea.length; i++) {
			ea[i].raiseSalary(10);
		}
		for (int i = 0; i < ea.length; i++) {
			System.out.println(ea[i].getName()+ea[i].getSalary()+ea[i].getHireDate());
		}
	}
	

}
class Employee{
	{
		id=nextId;
		System.out.println("init block 1!!!!!!");
		nextId++;
	}
	{
		id=nextId;
		System.out.println("init block 2!!!!!!");
		nextId++;
	}
	static{
		System.out.println("static block");
	}
	private String name="eric";;
	private double salary;
	private Date hireDate;
	private int id;
	private static int nextId=0;
	
	public Employee(String name, double salary, int year,int month,int day) {
		super();
		this.name = name;
		this.salary = salary;
		GregorianCalendar gc=new GregorianCalendar(year,month-1,day);
		this.hireDate = gc.getTime();
	}
	public Employee(){
		System.out.println("contruction method!");
		System.out.println("employee"+this.getId());
	}
	public Employee(String name,double salary){
		this.name=name;
		this.salary=salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public void raiseSalary(int byPresent){
		double raise=salary*byPresent/100;
		salary+=raise;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
