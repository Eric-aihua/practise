package com.eric.object;

public class GuarantedClearUp {
	public static void main(String[] args) {
		clearUp1();
		clearUp2();
	}
	
	static void clearUp1() {
		try {
			GuarantedClearUp gcu = null;
			gcu.toString();
			
		} catch (Exception e) {
			
		} finally {
			System.out.println("catch error 1!");
		}
	}
	
	static void clearUp2() {
		try {
			//finally haven't to invoke!
			System.exit(0);
			
		} catch (Exception e) {
			
		} finally {
			System.out.println("catch error 2!");
		}
	}
}
