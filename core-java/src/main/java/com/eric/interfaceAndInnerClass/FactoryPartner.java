package com.eric.interfaceAndInnerClass;

public class FactoryPartner {
	public static void main(String[] args) {
		process(new SerivceFactory1());
		process(new SerivceFactory2());
	}
	
	static void process(ServiceFactory serviceFactory) {
		serviceFactory.getService().run();
		serviceFactory.getService().jump();
	}
}

interface Service {
	public void run();
	
	public void jump();
}

interface ServiceFactory {
	Service getService();
}

class Service1 implements Service {
	
	public void run() {
		System.out.println("service1 run...");
		
	}
	
	public void jump() {
		System.out.println("service1 jump...");
		
	}
	
}

class SerivceFactory1 implements ServiceFactory {
	public Service getService() {
		return new Service1();
		
	}
}

class Service2 implements Service {
	
	public void run() {
		System.out.println("service2 run...");
		
	}
	
	public void jump() {
		System.out.println("service2 jump...");
	}
	
}

class SerivceFactory2 implements ServiceFactory {
	public Service getService() {
		return new Service2();
		
	}
}
