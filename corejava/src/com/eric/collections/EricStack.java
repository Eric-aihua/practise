package com.eric.collections;

import java.util.LinkedList;

import com.eric.collections.ListIteration.Person;

public class EricStack<T> {
	/**
	 * 
	 * */
	LinkedList<T> ll = new LinkedList<T>();

	public void push(T t) {
		ll.addFirst(t);
	}

	public T peek() {
		return ll.getFirst();
	}

	public T pop() {
		return ll.removeFirst();
	}

	public boolean isEmpty() {
		return ll.isEmpty();
	}

	public int size() {
		return ll.size();
	}

	@Override
	public String toString() {
		return ll.toString();
	}

	public static void main(String[] args) {
		EricStack<Person> es = new EricStack<Person>();
		for (int i = 0; i < 5; i++) {
			es.push(new Person(i + ""));
		}
		System.out.println(es);
		while (!es.isEmpty()) {
			System.out.println("peek:" + es.peek());
			System.out.println("pop:" + es.pop());
		}
	}

}
