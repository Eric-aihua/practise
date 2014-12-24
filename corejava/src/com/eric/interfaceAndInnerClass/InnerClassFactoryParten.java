package com.eric.interfaceAndInnerClass;
 

/**
 * 这个类主要用来说明用匿名内部类实现的工厂模式
 * @author Eric
 *
 */
public class InnerClassFactoryParten {
	public static void main(String[] args) {
		//result is null
		System.out.println(ServiceImplement1.getServiceFactory().getClass().getSimpleName());
		ServiceImplement1.getServiceFactory().getService().run();
		ServiceImplement1.getServiceFactory().getService().jump();
		ServiceImplement2.getServiceFactory().getService().run();
		ServiceImplement2.getServiceFactory().getService().jump();
    }
}

class ServiceImplement1 implements Service{
	private ServiceImplement1(){};

	public void run() {
		System.out.println("service1 run...");
		
	}
	
	public void  jump() {
		System.out.println("service1 jump...");
		
	}
	
	public static  ServiceFactory getServiceFactory(){
		return new ServiceFactory(){
			public Service getService() {
	            return new ServiceImplement1();
            }
			
		};
	}
}

class ServiceImplement2 implements Service{
	private ServiceImplement2(){};

	public void run() {
		System.out.println("service2 run...");
		
	}
	
	public void jump() {
		System.out.println("service2 jump...");
		
	}
	public static ServiceFactory getServiceFactory(){
		return new ServiceFactory(){

			public Service getService() {
	            return new ServiceImplement2();
            }
			
		};
	}
	
}

