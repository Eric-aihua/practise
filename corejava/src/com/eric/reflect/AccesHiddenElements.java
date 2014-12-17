package com.eric.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * this class mainly for explain how to access hidden method or field
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class AccesHiddenElements {
	public static void main(String[] args) {
		Inter inter = new InterImpl();
		inter.f();
		if (inter instanceof InterImpl) {
			// use class cast to method that in InterImpl class
			((InterImpl) inter).g();
			// can't access h method
			// ((InterImpl) inter).h();
		}
		// (1)javap -private InterImpl to get all method name eg h()
		// (2)use reflect to access private method
		accessMehodByName(inter, "h");
		accessFieldByName(inter, "name");
		Inter annousInter = new Inter() {
			public void f() {
				System.out.println("f() method in annousInter");
			}

			private void g() {
				System.out.println("g() method in annousInter");
			}

		};
		accessMehodByName(annousInter, "g");
	}

	static void accessMehodByName(Object obj, String methodName) {
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName);
			method.setAccessible(true);
			method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void accessFieldByName(Object obj, String methodName) {
		try {
			Field field = obj.getClass().getDeclaredField(methodName);
			field.setAccessible(true);
			System.out.println(field.getInt(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

interface Inter {
	public void f();
}

class InterImpl implements Inter {
	private int name = 10;

	public void f() {
		System.out.println("f() in InterImpl");
	}

	public void g() {
		System.out.println("g() in InterImpl");
	}

	private void h() {
		System.out.println("h() in InterImpl");
	}

}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
