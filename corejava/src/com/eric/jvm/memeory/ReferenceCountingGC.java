package com.eric.jvm.memeory;

public class ReferenceCountingGC {
	
	/**
	 * -verbose:gc -XX:+PrintGCDetails -Xms30M -Xmx30M -Xmn10M
	 * -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
	 * 
	 * -Xmn10M：设置年轻代大小为10M。整个JVM内存大小=年轻代大小 + 年老代大小 +
	 * 持久代大小。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。
	 * 控制进入老年区的对象大小
	 */
	public static final int	    _1M	    = 1024 * 1024;
	private ReferenceCountingGC	ref	    = null;
	private byte[]	            content	= new byte [_1M * 1];
	
	public void testGC() {
		ReferenceCountingGC a = new ReferenceCountingGC();
		ReferenceCountingGC b = new ReferenceCountingGC();
		a.ref = a;
		b.ref = b;
		a = null;
		b = null;
		System.gc();
		
	}
	
	public static void main(String[] args) {
		new ReferenceCountingGC().testGC();
	}
	
}
