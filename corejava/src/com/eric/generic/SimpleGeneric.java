package com.eric.generic;

public class SimpleGeneric{
	public static void main(String[] args) {
		Pari<String> p=new Pari<String>();
	}
}

class Pari<T>{
	private T first;
	private T second;
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	public Pari(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
	public Pari() {
		super();
	}
	public void setSecond1(Object o) {
		// TODO Auto-generated method stub
		return;
	}
	
}
