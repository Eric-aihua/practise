package com.eric.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Iterator 是个典型的内部类的应用，list.ittrator() 便是返回一个iterator内部类的对象，这个对象实现类Iterator 接口
 * 
 * */
public class IteratorParten {

	public static void main(String[] args) {
		ArrayList<Pet> al = new ArrayList<Pet>();
		Set<Pet> set = new HashSet<Pet>();
		for (int i = 0; i < 10; i++) {
			al.add(new Pet("pet:" + i));
			set.add(new Pet("pet:" + i));
		}
		printCollection(al.iterator());
		printCollection(set.iterator());
	}

	// could access iterator from any collection inherit class
	static void printCollection(Iterator<Pet> it) {
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	static class Pet {
		String name;

		public Pet(String name) {
			this.name = name;
		}

		public String toString() {
			return "Pet's name is:" + name;
		}
	}

}
