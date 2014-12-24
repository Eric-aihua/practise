package com.eric.enumtest;

public class SitchEnum {
	public static Direction	direction	= Direction.EAST;
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			changeDirection();
		}
	}
	
	public static void changeDirection() {
		switch (direction) {
		case EAST:
			direction = Direction.NOTRH;
			break;
		case NOTRH:
			direction = Direction.SOUTH;
			break;
		case SOUTH:
			direction = Direction.WEST;
			break;
		case WEST:
			direction = Direction.EAST;
			break;
			
		}
		System.out.println(direction);
	}
}
