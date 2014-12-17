package com.eric.enumtest;

public class CalUsedEnum {
	public static void main(String[] args) {
		for (Cal ope : Operation.values()) {
			int x = 10, y = 11;
			System.out.println("x " + ope.toString() + " y=" + ope.apply(x, y));
		}
	}
}

interface Cal {
	public int apply(int x, int y);
}


enum Operation implements Cal {
	ADD {
		public int apply(int x, int y) {
			return x + y;
		}
	},
	SUB {
		
		public int apply(int x, int y) {
			return x - y;
		}
		
	},
	TIMES {
		
		public int apply(int x, int y) {
			return x * y;
		}
		
	},
	DIV {
		
		public int apply(int x, int y) {
			return x / y;
		}
		
	};
}
