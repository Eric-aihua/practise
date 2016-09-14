package com.eric.generic;

import java.util.GregorianCalendar;

public class ParamGenericTest {
	public static void main(String[] args) {
		GregorianCalendar gc=new GregorianCalendar(1986,5,7);
		GregorianCalendar gc2=new GregorianCalendar(1987,5,7);
		GregorianCalendar gc3=new GregorianCalendar(1988,5,7);
		GregorianCalendar[] gcArr={gc,gc2,gc3};
		Pari<GregorianCalendar> p=Arrlg.minMax(gcArr);
		System.out.println(p.getFirst().getTime());
		System.out.println(p.getSecond().getTime());
	}
}
class Arrlg{
	public static <T extends Comparable> Pari<T> minMax(T[] a){
		T min=a[0];
		T max=a[0];
		if(a==null||a.length==0){
			return null;
		}
		for (int i = 0; i < a.length; i++) {
			if(a[i].compareTo(max)>0) max=a[i];
			if(a[i].compareTo(min)<0) min=a[i];
		}
		return new Pari<T>(max,min);
	}
}
