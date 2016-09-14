package com.eric.enumtest;

public class Paper implements Item {
	
	public Result Compete(Item item) {
		return item.eval(this);
	}
	
	public Result eval(Paper item) {
		return Result.DRAW;
	}
	
	public Result eval(Rock item) {
		return Result.WIN;
	}
	
	public Result eval(Scissors item) {
		return Result.LOSE;
	}
	
	@Override
	public String toString(){
		return "Paper";
	}
	
}
