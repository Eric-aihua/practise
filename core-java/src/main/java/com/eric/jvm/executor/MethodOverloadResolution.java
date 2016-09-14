package com.eric.jvm.executor;

/**
 * 静态分派的例子
 * @author Eric
 *
 */
public class MethodOverloadResolution {
	public static void main(String[] args) {
		Human human = new Human();
		Human man = new Man();
		Human women = new Women();
		MethodOverloadResolution mor = new MethodOverloadResolution();
		mor.hello(human);
		mor.hello(man);
		mor.hello(women);
		//静态类型发生变化
		mor.hello((Man)man);
		mor.hello((Women)women);
	}
	
	public void hello(Human human) {
		System.out.println("Human say hello");
	}
	
	public void hello(Man human) {
		
		System.out.println("Man say hello");
	}
	
	public static void hello(Women human) {
		
		System.out.println("Women say hello");
	}
	
}

class Human {}

class Man extends Human {}

class Women extends Human {}
