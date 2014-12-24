package com.eric.control;

public class ControlStatement {
	public static void main(String[] args) {
		int a = 5;
		// java does'nt allowed you to use a number as a boolean, even through
		// it's allow use in c/c++
		// if(a){
		// System.out.println("a");
		// }
		// boolean expression must produce a boolean value
		if (a == 5) {
			System.out.println("a==5");
		}
		
		while (condition()) {
			System.out.println("in while......");
		}
		System.out.println("exit while......");
		
		printChar();
		initisNull();
		
	}
	
	static boolean condition() {
		double num = Math.random() * 5;
		System.out.println("in condition method:" + num);
		return num > 2;
	}
	
	static void printChar(){
		for(char c=0;c<128;c++){
			System.out.println("(int)c:"+(int)c+"  c:"+c);
			int i=c;
			if(i%10==0){
				System.out.println();
			}
			
		}
	}
	
	static void initisNull(){
		int i=5;
		for(i=9;i<10;){
			System.out.println("for(;;)");
		}
	}
}
