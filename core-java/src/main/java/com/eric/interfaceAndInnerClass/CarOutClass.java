package com.eric.interfaceAndInnerClass;

import java.util.Random;

class Car {
	private String	name;
	
	public Car(String name) {
		super();
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}

public class CarOutClass {
	private Car[]	cars	= new Car [10];
	private int	  number	= 0;
	
	private void addCar(Car car) {
		if (number < 10) {
			cars[number++] = car;
		}
	}
	//下面的这个例子说明内部类可以访问外部类的私有成员
	class CarSelector {
		public void next() {
			number++;
		}
		
		public boolean hasMore() {
			// System.out.println("has More:"+number);
			return number < 10;
		}
		
		public Car current() {
			return cars[number];
		}
	}
	
	public CarSelector getSelector() {
		return new CarSelector();
	}
	
	private class Engineer {
		public void run() {
			System.out.println("Engineer run.....");
		}
	}
	
	public Engineer getEngineer() {
		return new Engineer();
	}
	
	public static void main(String[] args) {
		new CarOutClass().getEngineer().run();
		
		CarOutClass carOutClass = new CarOutClass();
		for (int i = 0; i < carOutClass.cars.length; i++) {
			carOutClass.cars[i] = new Car(new Random().nextInt(10) + " numbers");
		}
		
		CarOutClass.CarSelector carSelector = carOutClass.getSelector();
		while (carSelector.hasMore()) {
			System.out.println(carSelector.current());
			carSelector.next();
		}
	}
}
