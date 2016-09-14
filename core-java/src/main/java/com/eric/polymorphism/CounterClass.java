package com.eric.polymorphism;

class Count {
	private static int	count;
	final int	       id	    = count++;
	private int	       refcount	= 0;
	
	public Count() {
		System.out.println("Count create:" + this);
	}
	
	public void addCount() {
		refcount++;
	}
	
	public void dispose() {
		refcount--;
	}
	
	public String toString() {
		return "Count:" + id;
	}
	
	public int getRefcount() {
		return refcount;
	}
	
	public void setRefcount(int refcount) {
		this.refcount = refcount;
	}
	
}

class Counter {
	private Count	count;
	
	public Counter(Count count) {
		this.count = count;
		count.addCount();
		System.out.println(this);
	}
	
	public void dispose() {
		count.dispose();
		System.out.println(this);
	}
	
	public String toString() {
		return "Counter:" + count.getRefcount();
	}
}

public class CounterClass {
	public static void main(String[] args) {
		Count count = new Count();
		Counter[] counters = { new Counter(count), new Counter(count), new Counter(count), new Counter(count), new Counter(count), new Counter(count)};
		for (Counter counter : counters) {
			counter.dispose();
		}
	}
}
