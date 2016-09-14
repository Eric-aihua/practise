package com.eric.collections;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 这个类主要展示了ListIterator的使用 It can also produce the indexes of the next and
 * previous elements relative to where the iterator is pointing in the list, and
 * it can replace the last element that it visited using the set( ) method.
 * */
public class ListIteration {
	public static void main(String[] args) {
		LinkedList<Person> ll = new LinkedList<Person>();
		for (int i = 1; i < 10; i++) {
			ll.add(new Person("person" + i));
		}
		ListIterator<Person> list = ll.listIterator();
		while (list.hasNext()) {
			System.out.println(list.next());
			System.out.println("perview index is:" + list.previousIndex());
			System.out.println("after index is:" + list.nextIndex() + "\n");
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
		while (list.hasPrevious()) {
			System.out.println("perview index is:" + list.previousIndex());
			System.out.println(list.previous());
			System.out.println("after index is:" + list.nextIndex() + "\n");
		}

	}

	static class Person {
		private String name;

		public Person(String name) {
			this.name = name;
		}

		public String toString() {
			return "name is:" + name;
		}
	}
}
