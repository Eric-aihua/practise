package com.eric.string;

public class UnintendedRecursion {
	public static void main(String[] args) {
		UnintendedRecursion ur=new UnintendedRecursion();
		UnintendedRecursion.Test ut1=ur.new Test();
		UnintendedRecursion.Test2 ut2=ur.new Test2();
		//System.out.println("ut1"+ut1);
		System.out.println("ut2"+ut2);
	}

	class Test {
		@Override
		public String toString() {
			return "Heap address is:" + this;
		}
	}

	class Test2 {
		@Override
		public String toString() {
			return "Heap address is:" + super.toString();
		}
	}
}
