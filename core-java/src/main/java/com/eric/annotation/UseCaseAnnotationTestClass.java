package com.eric.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseAnnotationTestClass {
	@UseCase(id = 1, description = "this method for business!")
	public void business() {
		System.out.println("business");
	}
	
	@UseCase(id = 2, description = "this method for anylize!")
	public void anylize() {
		System.out.println("anylize");
	}
	
	@UseCase(id = 3, description = "this method for develop!")
	public void develop() {
		System.out.println("develop");
	}
	
	@UseCase(id = 4, description = "this method for test!")
	public void test() {
		System.out.println("test");
	}
	
	public static void main(String[] args) {
		List<Integer> useCaseIdList = new ArrayList<Integer>();
		Collections.addAll(useCaseIdList, 1, 2, 3, 4, 5);
		Method[] methods = UseCaseAnnotationTestClass.class.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
			UseCase uc = method.getAnnotation(UseCase.class);
			if (uc != null) {
				useCaseIdList.remove(Integer.valueOf(uc.id()));
				System.out.println(uc.description());
			}
		}
		for (int i = 0; i < useCaseIdList.size(); i++) {
			System.out.println(useCaseIdList.get(i) + " was not execute!");
		}
	}
	
}
