package com.eric.jvm.annotations.visitor;

public class Customer implements Visitor {

	@Override
	public void visit(Apple apple) {
		System.out.println("customer put apple into shop car");
	}

	@Override
	public void visit(Book book) {
		System.out.println("customer put book into shop car");
	}

}
