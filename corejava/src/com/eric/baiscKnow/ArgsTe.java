package com.eric.baiscKnow;

public class ArgsTe {
	public static void main(String[] args) {
		if(args[0].equals("h")){
			System.out.println("hello");
		}
		else{
			System.out.println("goodbye!");
		}
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
}
