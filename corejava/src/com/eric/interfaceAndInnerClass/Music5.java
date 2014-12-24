package com.eric.interfaceAndInnerClass;

import com.eric.polymorphism.Note;

interface Instrument {
	// Compile-time constant:
	int	VALUE	= 5; // static & final
	                 
	// Cannot have method definitions:
	void play(Note n); // Automatically public
	
	void adjust();
}

class Wind implements Instrument {
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	
	public String toString() {
		return "Wind";
	}
	
	public void adjust() {
		System.out.println(this + ".adjust()");
	}
}

class Percussion implements Instrument {
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	
	public String toString() {
		return "Percussion";
	}
	
	public void adjust() {
		System.out.println(this + ".adjust()");
	}
}

class Stringed implements Instrument {
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	
	public String toString() {
		return "Stringed";
	}
	
	public void adjust() {
		System.out.println(this + ".adjust()");
	}
}

class Brass extends Wind {
	public String toString() {
		return "Brass";
	}
}

class Woodwind extends Wind {
	public String toString() {
		return "Woodwind";
	}
}

public class Music5 {
	// Doesn’t care about type, so new types
	// added to the system still work right:
	static void tune(Instrument i) {
		i.play(Note.MIDDLE_C);
	}
	
	static void tuneAll(Instrument[] e) {
		for (Instrument i : e)
			tune(i);
	}
	
	public static void main(String[] args) {
		// Upcasting during addition to the array:
		Instrument[] orchestra = { new Wind(), new Percussion(), new Stringed(), new Woodwind()};
		tuneAll(orchestra);
	}
}
