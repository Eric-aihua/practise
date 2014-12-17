package com.eric.baiscKnow;

public class UserText {
	public static void main(String[] args) {
		User u=new User("eric");
		User u2=new User();
		System.out.println(u.toString()+"#");
		System.out.println(u);
		System.out.println(u2);
	}
}

class User{
	private String name;
	public User(){
		
	}
	
	public User(String name) {
		super();
		this.name = name;
	}

	public String toString(){
		return name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
