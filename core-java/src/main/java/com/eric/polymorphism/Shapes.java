package com.eric.polymorphism;

import java.util.Random;

public class Shapes {
	public static void main(String[] args) {
		Shape[] shapes = new Shape [10];
		for (int i = 0; i < shapes.length; i++) {
			shapes[i] = ShapeFactory.next();
			
		}
		for (Shape shape : shapes) {
			System.out.println(shape.getClass().getName()+ "##");
			shape.draw();
			shape.erase();
			shape.clean();
		}
	}
}

class Shape {
	public void draw() {}
	
	public void erase() {}
	
	public void clean() {
		System.out.println("Shape clean.....");
	}
	
}

class ShapeFactory {
	public static Shape next() {
		Shape shape;
		Random random = new Random();
		int result = random.nextInt(4);
		switch (result) {
		case 1:
			shape = new Circle();
			break;
		case 2:
			shape = new Square();
			break;
		case 3:
			shape = new Triangle();
			break;
		default:
			shape = new Shape();
			
		}
		return shape;
	}
}

class Circle extends Shape {
	
	@Override
	public void draw() {
		System.out.println("Cricle draw");
	}
	
	@Override
	public void erase() {
		System.out.println("Circle erase");
	}
	
	@Override
	public void clean() {
		System.out.println("Circle clean.....");
	}
}

class Square extends Shape {
	
	@Override
	public void draw() {
		System.out.println("Square draw");
	}
	
	@Override
	public void erase() {
		System.out.println("Square erase");
	}
}

class Triangle extends Shape {
	
	@Override
	public void draw() {
		System.out.println("Triangle draw");
	}
	
	@Override
	public void erase() {
		System.out.println("Triangle erase");
	}
}
