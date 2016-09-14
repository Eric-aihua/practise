package com.eric.clone;

public class CloneTest {
	public static void main(String[] args) {
		SimpleClone sc = new SimpleClone("Eric");
		SimpleClone sc2 = (SimpleClone) sc.clone();
		System.out.println("sc2.getName:" + sc2.getName());
		System.out.println("sc2:" + sc2 + "  \n sc:" + sc);
		
		Department d1 = new Department("IT");
		Company c1 = new Company(d1);
		Company c2 = (Company) c1.clone();
		System.out.println("c1:" + c1 + "\nc2:" + c2 + "\n");
		System.out.println("c1==c2:" + (c1 == c2));
		System.out.println("c1.department==c2.department:" + (c1.getDepartment() == c2.getDepartment()) + "\n\n");
		
		Department d2 = new Department("IT");
		Company2 c21 = new Company2(d2);
		Company2 c22 = (Company2) c21.clone();
		System.out.println("c21:" + c21 + "\nc22:" + c22 + "\n");
		System.out.println("c21==c22:" + (c21 == c22));
		System.out.println("c21.department==c22.department:" + (c21.getDepartment() == c22.getDepartment()));
	}
}

class SimpleClone implements Cloneable {
	private String	name;
	
	public SimpleClone(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}

// complex clone but not deep clone
class Company implements Cloneable {
	private Department	department;
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Company(Department department) {
		super();
		this.department = department;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

// complex clone and deep clone
class Company2 implements Cloneable {
	private Department	department;
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Company2(Department department) {
		super();
		this.department = department;
	}
	
	public Object clone() {
		try {
			Company2 company2 = (Company2) super.clone();
			company2.department = (Department) this.department.clone();
			return company2;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

class Department implements Cloneable {
	private String	name;
	
	public Department(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
