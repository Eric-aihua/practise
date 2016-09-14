package com.eric.object;

public class MethodReturnType {
	public static void main(String[] args) {
		nonReturn();
		System.out.println(new StaticField().str);
		System.out.println(new StaticField().str);
		System.out.println(StaticField.str);
	}
	
	public static void nonReturn() {
		System.out.println("nonReturn method.....");
		return;
	}
}

class StaticField {
	static StaticObject	str	= new StaticObject();
}

class StaticObject {
}
