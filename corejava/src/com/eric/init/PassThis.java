package com.eric.init;

public class PassThis {
	public static void main(String[] args) {
	    new Person().eat(new Apple());
    }
}

class Apple{
	Apple getPeel(){
		return Peel.removePeel(this);
	}
}
class Person{
	void eat(Apple apple){
		apple.getPeel();
		System.out.println("YEAH!");
	}
}
class Peel{
	static Apple removePeel(Apple apple){
		System.out.println("Remove peel!");
		return apple;
	}
}
