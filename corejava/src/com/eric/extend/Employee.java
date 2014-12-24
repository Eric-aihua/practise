package com.eric.extend;

public class Employee {
	private double salary;
	
	public Employee(){}
	
	public Employee(double salary){
		this.salary=salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	protected void ss(){
		System.out.println("employee");
	}
	

}
