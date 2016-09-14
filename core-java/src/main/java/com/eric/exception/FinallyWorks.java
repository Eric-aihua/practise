package com.eric.exception;

public class FinallyWorks {
	public static void main(String[] args) throws Exception {
		try {
			System.out.println("try1");
			throw new RuntimeException("try");
		} catch (RuntimeException ex) {
			ex.printStackTrace(System.out);
		} finally {
			System.out.println("finally");
		}
		System.out.println("syso after with catch RuntimeException");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			System.out.println("try2");
			throw new FinallyWorks().new InnerException();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} finally {
			System.out.println("finally2");
		}
		System.out.println("syso after with catch self define exception");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			int result = 10 / 0;
		} catch (Exception ex) {
			throw ex;
		} finally {
			System.out.println("finally3");
		}
		System.out.println("syso after with catch throw exception");
	}

	class InnerException extends Exception {

	}

}
