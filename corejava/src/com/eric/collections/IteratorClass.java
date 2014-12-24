package com.eric.collections;

import java.util.Iterator;

/**
 * if any class that implements Iterable interface, that can be used in foreach
 * 
 * @author Eric
 * 
 */
public class IteratorClass implements Iterable<String> {
	private int	       count	= 0;
	protected String[]	arrs	= "i'm chinese and is a soft engineer".split(" ");
	
	public IteratorClass() {}
	
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			public boolean hasNext() {
				return count < arrs.length;
			}
			
			public String next() {
				return arrs[count++];
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	public static void main(String args[]) {
		for (String s : new IteratorClass()) {
			System.out.println(s);
		}
	}
	
}
