package com.eric.enumtest;

public interface Item {
	public Result Compete(Item item);
	
	public Result eval(Paper item);
	
	public Result eval(Rock item);
	
	public Result eval(Scissors item);
}
