/**
 * 
 */
package com.eric.enumtest;

import static com.eric.enumtest.AlarmPoints.BATHROOM;
import static com.eric.enumtest.AlarmPoints.KITCHEN;
import static com.eric.enumtest.AlarmPoints.OFFICE1;
import static com.eric.enumtest.AlarmPoints.OFFICE2;
import static com.eric.enumtest.AlarmPoints.OFFICE4;

import java.util.EnumSet;

/**
 * @author Eric
 * 
 */
public class EnumSets {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		enumSetMethod();
	}
	
	public static void enumSetMethod() {
		EnumSet<AlarmPoints> es = EnumSet.noneOf(AlarmPoints.class);
		es.add(BATHROOM);
		System.out.println("initialize:" + es);
		es.addAll(es.of(KITCHEN, OFFICE1));
		System.out.println("added elements:" + es);
		es = EnumSet.allOf(AlarmPoints.class);
		System.out.println("add all AlarmPoint enum:" + es);
		es=EnumSet.range(AlarmPoints.OFFICE1, OFFICE4);
		System.out.println("rang office1-office4:"+es);
		es.removeAll(EnumSet.of(OFFICE1, OFFICE2));
		System.out.println("remove office1 and office2:"+es);
	}
	
}
