package com.eric.jvm.annotations.visitor;

public class Apple implements Product {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
