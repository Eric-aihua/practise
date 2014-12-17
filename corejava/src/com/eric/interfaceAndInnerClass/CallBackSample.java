package com.eric.interfaceAndInnerClass;

/**
 * SupportService interface support 3 operation
 * */
interface SupportService {
	public void query();
	
	public void add();
	
	public void delete();
}

/**
 * SupportCaller class have a SupportService field
 * */
class SupportCaller {
	private SupportService	ss;
	
	public void setSupportServices(SupportService ss) {
		this.ss = ss;
	};
	
	/**
	 * in support method to call SupportService's method
	 * */
	public void support() {
		ss.query();
		ss.add();
		ss.delete();
	}
}

/**
 * until now, SupportService not be implements, in CallBackSample use inner
 * class to implements call back
 * */

public class CallBackSample {
	public static void main(String[] args) {
		SupportCaller sc = new SupportCaller();
		sc.setSupportServices(new SupportService() {
			
			public void query() {
				System.out.println("query operation");
			}
			
			public void delete() {
				
				System.out.println("delete operation");
			}
			
			public void add() {
				
				System.out.println("add operation");
			}
		});
		sc.support();
	}
}
