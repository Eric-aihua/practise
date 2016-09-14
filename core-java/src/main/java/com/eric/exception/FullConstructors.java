/**
 * 
 */
package com.eric.exception;

/**
 * 本例子主要用来展示
 * （1）带参数的异常以及默认构造器的异常
 * （2）e.printStackTrace(System.out);与e.printStackTrace();的区别
 * @author Administrator
 * 
 */

class MyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6615440079867707473L;

	public MyException() {
		super();
	}

	public MyException(String msg) {
		super(msg);
	}
}

public class FullConstructors {

	public static void f() throws MyException {
		throw new MyException();
	}

	public static void g() throws MyException {
		throw new MyException("Constructor exception!");
	}

	public static void main(String[] args) {
		try {
			f();
		} catch (MyException e) {
			System.out.println("f() exception was catch...");
			
			e.printStackTrace(System.out);
		}
		try {
			g();
		} catch (MyException e) {
			System.out.println("g() exception was catch...");
			e.printStackTrace();
		}

	}
}
