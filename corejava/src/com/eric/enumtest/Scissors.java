package com.eric.enumtest;

public class Scissors implements Item {
	
	public Result Compete(Item item) {
		return item.eval(this);
	}
	
	public Result eval(Paper item) {
		return Result.WIN;
	}
	
	public Result eval(Rock item) {
		return Result.LOSE;
	}
	
	public Result eval(Scissors item) {
		return Result.DRAW;
	}
	@Override
	public String toString(){
		return "Scissors";
	}
}
