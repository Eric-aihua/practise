package com.eric.interfaceAndInnerClass;

interface Counter {
	public void add();
}

/**
 * 这个类主要说明通过匿名内部类和局部内部类的方式来实现内部类，以及说明了内部类可以不受限制的访问外围类的成员
 * @author Eric
 *
 */
public class LocalInnerClass2 {
	private int	count	= 0;
	
	public Counter getCounter(String counterName) {
		class Counter1 implements Counter {
			
			public void add() {
				count++;
				System.out.println("count in Counter1:" + count);
			}
			
			public Counter1(String counterName) {
				System.out.println("output by:" + counterName);
			}
			
			class Count1Inner{
			}
			
		}
		
		return new Counter1(counterName);
	}
	
	public Counter getCounter2(final String CounterName) {
		return new Counter() {
			{
				System.out.println("output by:" + CounterName);
			}
			
			public void add() {
				count++;
				System.out.println("count in Count2:" + count);
			}
			
		};
	}
	
	public static void main(String[] args) {
		LocalInnerClass2 lc = new LocalInnerClass2();
		Counter c1 = lc.getCounter("COUNTER1");
		Counter c2 = lc.getCounter2("COUNTER2");
		for (int i = 0; i < 5; i++) {
			c1.add();
			c2.add();
		}
	}
}
