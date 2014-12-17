package com.eric.concurrency;

import java.util.GregorianCalendar;

public class test {
	public static void main(String[] args) {
		GregorianCalendar gregorianCalendar=new GregorianCalendar();
		int year=gregorianCalendar.get(GregorianCalendar.YEAR);
		int month=gregorianCalendar.get(GregorianCalendar.MONTH);
		int day=gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
		System.out.println(year+":"+month+":"+day);
		GregorianCalendar gregorianCalendar2=new GregorianCalendar(year, month, day, 11, 40);
		long time=System.currentTimeMillis();
		long time2=gregorianCalendar2.getTimeInMillis();
		if(time==time2){
			System.out.println("call eat");
		}
	}
}
