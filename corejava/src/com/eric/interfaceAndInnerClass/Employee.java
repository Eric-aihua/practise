package com.eric.interfaceAndInnerClass;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Cloneable {

	{
		id = nextId;
		System.out.println("init block 1!!!!!!");
		nextId++;
	}
	{
		id = nextId;
		System.out.println("init block 2!!!!!!");
		nextId++;
	}
	static {
		System.out.println("static block");
	}
	private String name = "eric";;
	private double salary;
	private Date hireDate;
	private int id;
	private static int nextId = 0;

	public Employee(String name, double salary, int year, int month, int day) {
		super();
		this.name = name;
		this.salary = salary;
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		this.hireDate = gc.getTime();
	}

	public Employee() {
		System.out.println("contruction method!");
		System.out.println("employee" + this.getId());
	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Employee clo=(Employee)super.clone();
		clo.hireDate=(Date)hireDate.clone();
		return clo;
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

	public void raiseSalary(int byPresent) {
		double raise = salary * byPresent / 100;
		salary += raise;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
