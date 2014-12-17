package com.eric.jvm.executor;

/**
 * 关于Override的例子
 * 
 * @author Eric
 * 
 */
public class MethodOverrideDemonstrate {
	
	public static void main(String[] args) {
		MethodOverrideDemonstrate demonstrate = new MethodOverrideDemonstrate();
		Parent parent = demonstrate.new Parent();
		Parent father = demonstrate.new Father();
		Parent mother = demonstrate.new Mother();
		
		parent.printInfo();
		father.printInfo();
		mother.printInfo();
	}
	
	class Parent {
		public void printInfo() {
			System.out.println("Parent output....");
		}
	}
	
	class Father extends Parent {
		public void printInfo() {
			System.out.println("Father output....");
		}
	}
	
	class Mother extends Parent {
		public void printInfo() {
			System.out.println("Mother output....");
		}
	}
	
}
