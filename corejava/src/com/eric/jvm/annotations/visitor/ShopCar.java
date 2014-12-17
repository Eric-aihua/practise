package com.eric.jvm.annotations.visitor;

import java.util.ArrayList;
import java.util.List;

public class ShopCar {
	public List<Product> productList;

	public ShopCar() {
		productList = new ArrayList<Product>();
	}

	public void accept(Visitor visiotr) {
		for (Product product : productList) {
			product.accept(visiotr);
		}
	}

	public void addProduct(Product product) {
		productList.add(product);
	}

	public void removeProduct(Product product) {
		productList.remove(product);
	}
}
