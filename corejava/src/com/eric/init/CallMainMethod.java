package com.eric.init;

public class CallMainMethod {
	public static void main(String[] args) {
		callMain("sun","ai","hua");
	}
	static void callMain(String ...str){
		Main.main(str);
	}
}

class Main {
	public static void main(String[] args) {
		for (String string : args) {
			System.out.println(string);
		}
	}
}
