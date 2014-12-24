package com.eric.control;

public class GoToExec {
	public static void main(String[] args) {
		int i = 0;
		outer: // Can’t have statements here
		for (; true;) { // infinite loop
			inner: // Can’t have statements here
			for (; i < 10; i++) {
				System.out.println("i = " + i);
				if (i == 2) {
					System.out.println("continue");
					continue;
					//break outer;
				}
				if (i == 3) {
					System.out.println("break");
					i++; // Otherwise i never
					// gets incremented.
					break;
				}
				if (i == 7) {
					System.out.println("i==7 continue outer");
					i++; // Otherwise i never
					// gets incremented.
					continue outer;
				}
				if (i == 8) {
					System.out.println(" i=8 break outer");
					break outer;
				}
				for (int k = 0; k < 5; k++) {
					if (k == 3) {
						System.out.println(" k continue inner");
						continue inner;
					}
				}
			}
		}
		System.out.println("finished");
		// Can’t break or continue to labels here
	}
}
