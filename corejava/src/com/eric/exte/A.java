package com.eric.exte;

class B {
	public int a;
	public String s;
	public final int b=0;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	@ Override
	public String toString(){
		return "toString";
	}
//	public void setB(int b){
//		this.b=b;
//	}

}

class A{
	public static void main(String[] args) {
		B b=new B();
		C c=new C();
		if(c instanceof C){
			
		}
		b.setA(10);
		//b.setB(5);
		System.out.println(b.getClass().getSimpleName());
		System.out.println(b);
		System.out.println(System.out);
		System.out.println(b.getClass().getFields());
	}
	@Override
	public boolean equals(Object other){
		return false;
	}
}

class C{}