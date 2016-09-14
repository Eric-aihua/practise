package com.eric.jvm.annotations.visitor;

public class Operator implements Visitor {

	@Override
	public void visit(Apple apple) {
		System.out.println("operator read price from apple");
	}

	@Override
	public void visit(Book book) {
		System.out.println("operator read price from book");
	}

}
