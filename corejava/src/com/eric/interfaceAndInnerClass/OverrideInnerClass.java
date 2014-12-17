package com.eric.interfaceAndInnerClass;

public class OverrideInnerClass {
	public static void main(String[] args) {
		new Outer2Class();
    }
}


class OuterClass{
	class InnerClass1{
		public InnerClass1(){
			System.out.println("OuterClass.InnerClass1");
		}
	}
	public OuterClass() {
		System.out.println("outer class construct...");
		new InnerClass1();
    }
}

class Outer2Class extends OuterClass{
	class InnerClass1{
		public InnerClass1(){
			System.out.println("Outer2Class.InnerClass1");
		}
	}
}
//在子类中虽然看起来是覆盖了父类中的内部类，可是在在父类的构造器中调用的还是父类的内部类，说明内部类是独立的
//outer class construct...
//OuterClass.InnerClass1
