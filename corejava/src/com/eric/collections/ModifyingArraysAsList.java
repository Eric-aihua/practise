package com.eric.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ModifyingArraysAsList {
	public static void main(String[] args) {
		Integer[] ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		/**
		 * if (elementData.getClass() != Object[].class) elementData =
		 * Arrays.copyOf(elementData, size, Object[].class);
		 * 
		 * */
		ArrayList<Integer> al = new ArrayList<Integer>(Arrays.asList(ints));
		Random random = new Random();
		Collections.shuffle(al, random);
		System.out.println(al);
		System.out.println(Arrays.asList(ints));
		/**
		 * 
		 * ArrayList(E[] array) { if (array==null) throw new
		 * NullPointerException(); a = array; }
		 * */
		List<Integer> al2 = Arrays.asList(ints);
		Collections.shuffle(al2, random);
		System.out.println(al2);
		System.out.println(Arrays.asList(ints));
	}
}
