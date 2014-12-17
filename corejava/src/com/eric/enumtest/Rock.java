package com.eric.enumtest;

public class Rock implements Item{

	public Result Compete(Item item) {
	    return item.eval(this);
    }

	public Result eval(Paper item) {
	    return Result.LOSE;
    }

	public Result eval(Rock item) {
	    return Result.DRAW;
    }

	public Result eval(Scissors item) {
	    return Result.WIN;
    }
	@Override
	public String toString(){
		return "Rock";
	}
}
