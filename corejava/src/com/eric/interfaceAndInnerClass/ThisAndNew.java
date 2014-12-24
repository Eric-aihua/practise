package com.eric.interfaceAndInnerClass;


/**
 * 知识点
 * （1）内部类不仅可以访问外部类的私有属性还可以访问外部类的私有方法
 * （2）可以通过OuterThis.InnierThis oi = ot.new InnierThis();的方式创建内部类对象
 * （3）在内部类中可以通过return OuterThis.this;方式返回外部类的应用
 * @author Eric
 *
 */
public class ThisAndNew {
	public static void main(String[] args) {
		OuterThis ot = new OuterThis();
		OuterThis.InnierThis oi = ot.new InnierThis();
		System.out.println(oi.getOuter());
		System.out.println(oi.getInner());
		oi.syso();
	}
}

class OuterThis {
	private String	name	= "adward";
	
	private void syso() {
		System.out.println("Outer output");
	}
	
	public String getName() {
		return name;
	}
	
	class InnierThis {
		OuterThis getOuter() {
			return OuterThis.this;
		}
		
		InnierThis getInner() {
			return this;
		}
		
		public void syso() {
			// syso();
			System.out.println("inner class to access Outclass private method...");
			OuterThis ot = new OuterThis();
			ot.syso();
			System.out.println("inner class to modify outclass private field" + "\noriginal is:" + ot.getName());
			ot.name = "Eric";
			System.out.println("after modify is:" + ot.getName());
			
		}
		
		@Override
		public String toString() {
			return "INNER:";
		}
	}
	
	@Override
	public String toString() {
		return "OUTER:";
	}
}
