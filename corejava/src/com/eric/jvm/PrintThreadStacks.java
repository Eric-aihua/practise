package com.eric.jvm;

public class PrintThreadStacks {
	public static void main(String args[]) {
		printThreadStacks();
	}

	public static void printThreadStacks() {
		Thread.getAllStackTraces().entrySet();
	}
}