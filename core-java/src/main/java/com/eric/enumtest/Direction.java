package com.eric.enumtest;

public enum Direction {
	// Java forces you to define the instances as the first thing in the enum,
	// and the end of enum element must be end of ";"
	EAST("east.....") {
		@Override
		void printinfo() {
			System.out.println("1111");
		}
	},
	WEST("west...") {
		@Override
		void printinfo() {
			System.out.println("2222");
			
		}
	},
	SOUTH("south...") {
		@Override
		void printinfo() {
			System.out.println("3333");
			
		}
	},
	NOTRH("north...") {
		@Override
		void printinfo() {
			System.out.println("4444");
			
		}
	};
	private String	info;
	
	private Direction(String info) {
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}
	
	public String toString() {
		return "DIRECTION:" + info;
	}
	
	abstract void printinfo();
}
