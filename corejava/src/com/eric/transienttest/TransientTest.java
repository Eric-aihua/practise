package com.eric.transienttest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientTest {
	public static void main(String[] args) {
		Person p1 = new Person("Eric", "123456");
		Person2 p2 = new Person2("Simon", "123456");
		
		readAndWriteObject(p1);
		readAndWriteObject(p2);
		
	}
	
	static void readAndWriteObject(People people) {
		
		System.out.println("before write file:" + people);
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("people.out"));
			o.writeObject(people);
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// To read the object back, we can write
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("people.out"));
			People people2 = (People) in.readObject();
			System.out.println("get object from file:" + people2);
		} catch (Exception e) {// deal with exception}
		
		}
	}
}

class People {
	
}

class Person extends People implements Serializable {
	/**
     * 
     */
	private static final long	serialVersionUID	= -9221500999229526064L;
	private String	          name;
	private String	          pwd;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Person(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "person1 name:" + name + " pwd:" + pwd;
	}
	
}

class Person2 extends People implements Serializable {
	/**
     * 
     */
	private static final long	serialVersionUID	= -3195698226754948666L;
	private String	          name;
	private transient String	pwd;	                                     // 这个不会被序列化和反序列化
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Person2(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "person2 name:" + name + " pwd:" + pwd;
	}
}
