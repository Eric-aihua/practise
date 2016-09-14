package com.eric.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class ObjectFileText {
	public static void main(String[] args) {
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("employee.dat"));
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("employee.dat"));
			Manager m=new Manager("eric",2342,1986,11,2);
			Employee e1=new Employee("simon",1231,1876,10,2);
			Employee e2=new Employee("jack",1232,1873,11,2);
			oos.writeObject(m);
			oos.writeObject(e1);
			oos.writeObject(e2);
			System.out.println(new File("employee.dat").length());
			oos.close();
			
			Employee ee1=(Employee)ois.readObject();
			Employee ee2=(Employee)ois.readObject();
			Employee ee3=(Employee)ois.readObject();
			System.out.println(ee1);
			System.out.println(ee2);
			System.out.println(ee3);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
class Employee implements Serializable{
	private String name;
	private double salary;
	private Date hireDay;
	public Employee(String name, double salary, int year,int month,int day) {
		super();
		this.name = name;
		this.salary = salary;
		GregorianCalendar gc=new GregorianCalendar(year,month-1,day);
		this.hireDay = gc.getTime();
	}
	public Employee() {
		super();
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
	public Date getHireDay() {
		return hireDay;
	}
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	@Override
	public String toString() {
		return this.getClass()+getName()+getSalary()+getHireDay();
	}
	public void raiseSalary(double present){
		double s=salary*present/100;
		salary+=s;
	}
}
class Manager extends Employee{
	public Manager(String name,double salary,int y,int m,int d){
		super(name, salary, y, m, d);
	}
}
