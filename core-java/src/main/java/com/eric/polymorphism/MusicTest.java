package com.eric.polymorphism;

public class MusicTest {
	public static void main(String[] args) {
		Instrument in = new Wind();
		Instrument base = new Base();
		MusicTest.turn(in);
		MusicTest.turn(base);
	}
	
	static void turn(Instrument in) {
		in.play(Note.B_FLAT);
	}
}

class Instrument {
	public void play(Note note) {
		System.out.println("Instrument play:" + note);
	}
}

class Base extends Instrument{}

class Wind extends Instrument {
	public void play(Note note) {
		System.out.println("Wind play:" + note);
	}
	
}


