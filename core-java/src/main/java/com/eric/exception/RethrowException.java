package com.eric.exception;

public class RethrowException {
	public static void main(String[] args) {
		
		try {
			// g0();
			// g1();
			// g2();
			// g3();
			g6();
		} catch (Exception e) {
			System.out.println("##CAUSE Message:###" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/*
	 * method f() exception was caugth by g4() method::exception chaining
	 * exception was caugth by g5() method::exception chaining
	 * com.eric.exception.RethrowException$Test2Exception at
	 * com.eric.exception.RethrowException.g5(RethrowException.java:32) at
	 * com.eric.exception.RethrowException.g6(RethrowException.java:21) at
	 * com.eric.exception.RethrowException.main(RethrowException.java:11) Caused
	 * by: com.eric.exception.RethrowException$Test2Exception at
	 * com.eric.exception.RethrowException.g4(RethrowException.java:43) at
	 * com.eric.exception.RethrowException.g5(RethrowException.java:29) ... 2
	 * more Caused by: com.eric.exception.RethrowException$Test1Exception:
	 * #############ERROR############## at
	 * com.eric.exception.RethrowException.f(RethrowException.java:125) at
	 * com.eric.exception.RethrowException.g4(RethrowException.java:40) ... 3
	 * more
	 */
	public static void g6() throws Test2Exception {
		try {
			g5();
		} catch (Test2Exception e) {
			throw e;
		}
	}
	
	public static void g5() throws Test2Exception {
		try {
			g4();
		} catch (Test2Exception e) {
			System.out.println("exception was caugth by g5() method::exception chaining");
			RethrowException.Test2Exception t2 = new RethrowException().new Test2Exception();
			t2.initCause(e);
			throw t2;
		}
	}
	
	public static void g4() throws Test2Exception {
		try {
			f();
		} catch (Test1Exception e) {
			System.out.println("exception was caugth by g4() method::exception chaining");
			RethrowException.Test2Exception t2 = new RethrowException().new Test2Exception();
			t2.initCause(e);
			throw t2;
		}
	}
	
	/*
	 * method f() exception was caugth by g1() method
	 * com.eric.exception.RethrowException$Test1Exception:
	 * #############ERROR############## at
	 * com.eric.exception.RethrowException.f(RethrowException.java:78) at
	 * com.eric.exception.RethrowException.g1(RethrowException.java:49) at
	 * com.eric.exception.RethrowException.main(RethrowException.java:7)
	 */
	public static void g1() throws Test1Exception {
		try {
			f();
		} catch (Test1Exception e) {
			System.out.println("exception was caugth by g1() method");
			throw e;
		}
	}
	
	/*
	 * 原始的異常信息將丟失
	 * 
	 * method f() exception was caugth by g2() method::use fillInstackTrace
	 * method com.eric.exception.RethrowException$Test1Exception:
	 * #############ERROR############## at
	 * com.eric.exception.RethrowException.g2(RethrowException.java:69) at
	 * com.eric.exception.RethrowException.main(RethrowException.java:8)
	 */
	public static void g2() throws Exception {
		try {
			f();
		} catch (Test1Exception e) {
			System.out.println("exception was caugth by g2() method::use fillInstackTrace method");
			throw (Exception) e.fillInStackTrace();
		}
	}
	
	/*
	 * 原始的異常信息將丟失
	 * 
	 * method f() exception was caugth by g3() method::change exception object
	 * com.eric.exception.RethrowException$Test2Exception: rethrow by t2 at
	 * com.eric.exception.RethrowException.g3(RethrowException.java:86) at
	 * com.eric.exception.RethrowException.main(RethrowException.java:9)
	 */
	public static void g3() throws Exception {
		try {
			f();
		} catch (Test1Exception e) {
			System.out.println("exception was caugth by g3() method::change exception object");
			// e.printStackTrace();
			RethrowException.Test2Exception t2 = new RethrowException().new Test2Exception("rethrow by g3");
			throw t2;
		}
	}
	
	/*
	 * 通过initCause()方法，把原始的异常堆栈信息保存下来。 下面的异常信息中包含了原始的異常信息
	 * RethrowException.f(RethrowException.java:125)
	 * 
	 * 
	 * method f() com.eric.exception.RethrowException$Test2Exception: rethrow by
	 * g0 at com.eric.exception.RethrowException.g0(RethrowException.java:117)
	 * at com.eric.exception.RethrowException.main(RethrowException.java:10)
	 * Caused by: com.eric.exception.RethrowException$Test1Exception:
	 * #############ERROR############## at
	 * com.eric.exception.RethrowException.f(RethrowException.java:125) at
	 * com.eric.exception.RethrowException.g0(RethrowException.java:113) ... 1
	 * more exception was caugth by g0() method::change exception object
	 */
	public static void g0() throws Exception {
		try {
			f();
		} catch (Test1Exception e) {
			System.out.println("exception was caugth by g0() method::change exception object");
			// e.printStackTrace();
			RethrowException.Test2Exception t2 = new RethrowException().new Test2Exception("rethrow by g0");
			t2.initCause(e);
			throw t2;
		}
	}
	
	public static void f() throws Test1Exception {
		System.out.println("method f()");
		throw new RethrowException().new Test1Exception("#############ERROR##############");
	}
	
	class Test1Exception extends Exception {
		
		private static final long	serialVersionUID	= -2656978134207266579L;
		
		public Test1Exception(String message) {
			super(message);
		}
	}
	
	class Test2Exception extends Exception {
		
		private static final long	serialVersionUID	= -4063317779983989554L;
		private String		      msg;
		
		public Test2Exception() {}
		
		public Test2Exception(String msg) {
			super(msg);
			this.msg = msg;
		}
	}
	
}
