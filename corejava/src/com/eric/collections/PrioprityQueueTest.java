package com.eric.collections;

import java.util.GregorianCalendar;
import java.util.PriorityQueue;

public class PrioprityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<GregorianCalendar> pq=new PriorityQueue<GregorianCalendar>();
		pq.add(new GregorianCalendar(1986,7,15));
		pq.add(new GregorianCalendar(1983,7,4));
		pq.add(new GregorianCalendar(1982,7,25));
		pq.add(new GregorianCalendar(1989,7,6));
		for (GregorianCalendar gregorianCalendar : pq) {
			System.out.println(gregorianCalendar.get(GregorianCalendar.YEAR));
		}
		while(!pq.isEmpty()){
			System.out.println(pq.remove().get(GregorianCalendar.YEAR));
			
		}
	}
}
