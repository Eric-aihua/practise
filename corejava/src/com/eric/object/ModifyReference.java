package com.eric.object;

public class ModifyReference {
	public static void main(String args[]){
		Object1 obj1=new Object1();
		Object2 obj2=obj1.obj;
		obj2.setName("simon");
		System.out.println(obj1.obj.getName());
	}
}

class Object1 {
	Object2	obj	= new Object2();
}

class Object2 {
	private String	name	= "Eric";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
