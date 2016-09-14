package com.eric.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BasicConception {
	public static void main(String args[]) {
		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		Integer[] moreInts = { 6, 7, 8, 9, 10};
		collection.addAll(Arrays.asList(moreInts));
		System.out.println(collection);
		// Runs significantly faster, but you can’t
		// construct a Collection this way:
		Collections.addAll(collection, 11, 12, 13, 14, 15);
		System.out.println(collection);
		Collections.addAll(collection, moreInts);
		System.out.println(collection);
		// Produces a list "backed by" an array:
		List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
		list.set(1, 99); // OK -- modify an element
		System.out.println(list);
		// list.add(21); // Runtime error because the
		// underlying array cannot be resized.
	}
}
