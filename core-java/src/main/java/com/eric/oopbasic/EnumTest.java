package com.eric.oopbasic;

import java.lang.reflect.Array;

public class EnumTest {
	public static void main(String[] args) {
		Object o=Size.values();
		Class s=o.getClass();
		if(!s.isArray()){
			System.out.println("exit");
		}
		int len=Array.getLength(o);
		for (int i = 0; i < len; i++) {
			Object ov=Array.get(o, i);//�õ���Ӧλ�õ�ֵ
			System.out.println(ov);
		}
		System.out.println("method 2");
	}

}
