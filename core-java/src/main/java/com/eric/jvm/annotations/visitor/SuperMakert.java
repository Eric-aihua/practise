package com.eric.jvm.annotations.visitor;

public class SuperMakert {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShopCar sc = new ShopCar();
		sc.addProduct(new Book());
		sc.addProduct(new Apple());
		sc.accept(new Customer());
		sc.accept(new Operator());
	}

}
