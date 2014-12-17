package com.eric.jvm.annotations.visitor;

public class Book implements Product {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
