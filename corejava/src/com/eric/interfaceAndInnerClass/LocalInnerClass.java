package com.eric.interfaceAndInnerClass;

public class LocalInnerClass {
	private String name = "name";

	public static void main(String[] args) {
		IncludeInnerClass(5, 6);
		//innerClass ic=new innerClass();
	}

	public static void IncludeInnerClass(final int a, int b) {
		final String name3 = "name1";
		String name2;
		class innerClass {
			public void prin() {
				System.out.println(name3);
				System.out.println(a);
			}

			private String name = "inner name";

			public String getName() {
				return name;
			}

			String name2 = "name2";
		}
		innerClass ic=new innerClass();
		System.out.println(ic.name);
		ic.prin();

	}

}
