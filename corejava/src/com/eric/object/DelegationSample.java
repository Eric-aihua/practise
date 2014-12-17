package com.eric.object;

public class DelegationSample {
	public static void main(String[] args) {
		SpaceShipDelegation sd = new SpaceShipDelegation();
		sd.forward().right().left().back();
	}
}

class SpaceShipControls {
	public void forward() {
		System.out.println("Space Ship forward!");
	}
	
	public void left() {
		System.out.println("space ship turn to left...");
	}
	
	public void right() {
		System.out.println("space ship turn to right...");
	}
	
	public void back() {
		System.out.println("space ship turn to back...");
	}
}

class SpaceShipDelegation {
	SpaceShipControls	ssc	= new SpaceShipControls();
	
	public SpaceShipDelegation forward() {
		ssc.forward();
		System.out.println("SpaceShipDelegation forward!");
		return this;
	}
	
	public SpaceShipDelegation left() {
		ssc.left();
		System.out.println("SpaceShipDelegation left...");
		return this;
	}
	
	public SpaceShipDelegation right() {
		ssc.right();
		System.out.println("SpaceShipDelegation right...");
		return this;
	}
	
	public SpaceShipDelegation back() {
		ssc.back();
		System.out.println("SpaceShipDelegation back...");
		return this;
	}
}
