package com.eric.polymorphism;

public class CycleTest {
	public static void main(String[] args) {
		Cycle cycle=new Unicycle();
		CycleTest.rideCrycle(cycle);
    }
	
	static void rideCrycle(Cycle cycle){
		cycle.ride();
	}
	
}

class Cycle{
	public void ride(){
		System.out.println("Cycle ride()");
	}
}

class Unicycle extends Cycle{
	public void ride(){
		System.out.println("Unicycle ride()");
	}
}

class BiCycle extends Cycle{
	public void ride(){
		System.out.println("BiCycle ride()");
	}
}
