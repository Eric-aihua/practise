package com.eric.interfaceAndInnerClass;

class Wrapping {
	private int	x;
	
	int value() {
		return x;
	}
	
	public Wrapping(int x) {
		System.out.println("Wrapping's x is:" + x);
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
}

public class Parcel8 {
	public Wrapping wrapping(int x) {
		// Base constructor call:
		return new Wrapping(x) { // Pass constructor argument.
			public int value() {
				return super.value() * 47;
			}
		}; // Semicolon required
	}
	
	public static void main(String[] args) {
		Parcel8 p = new Parcel8();
		Wrapping w = p.wrapping(10);
		System.out.println(w.value());
	}
} // /:~ 