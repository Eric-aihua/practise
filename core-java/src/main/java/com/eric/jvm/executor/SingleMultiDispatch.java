package com.eric.jvm.executor;

public class SingleMultiDispatch {
	
	/**
	 * 演示单分派,多分派
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Parent parent=new Parent();
		Parent son=new Son();
		parent.buy(new IPhone());
		son.buy(new Sumsung());
	}
	
	
	
	static class Parent{
		public void buy(IPhone ipPhone){System.out.println("parent buy iphone");};
		public void buy(Sumsung sumsung){System.out.println("parent buy sumsung");};
	}
	
	static class Son extends Parent{
		public void buy(IPhone ipPhone){System.out.println("son buy iphone");};
		public void buy(Sumsung sumsung){System.out.println("son buy sumsung");};
	}
	
	static class Sumsung {}
	static class IPhone {}
	
}
