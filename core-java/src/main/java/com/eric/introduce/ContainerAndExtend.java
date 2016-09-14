package com.eric.introduce;

public class ContainerAndExtend {
	public void methodA() {
		System.out.println("parent class method A output");
	}
	
	public void methodB() {
		System.out.println("parent class method B output");
	}
	
	public static void main(String args[]) {
		ContainerExec coExec = new ContainerExec();
		SunClass sunClass = new SunClass();
		coExec.printA();
		coExec.printB();
		
		sunClass.methodA();
		sunClass.methodB();
	}
}

class ContainerExec {
	ContainerAndExtend	containerAndExtend	= new ContainerAndExtend();
	
	public void printA() {
		containerAndExtend.methodA();
	}
	
	public void printB() {
		containerAndExtend.methodB();
	}
}

class SunClass extends ContainerAndExtend {
	
}