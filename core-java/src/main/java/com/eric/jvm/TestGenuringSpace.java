package com.eric.jvm;

public class TestGenuringSpace {

	/**
	 * 验证Eden为默认的对象分配区 -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 * -XX:+PrintGCDetails
	 */
	public static final int _1M = 1024 << 10;

	public static void main(String[] args) {
		testAllolaction();
	}

	public static void testAllolaction() {
		byte[] all1, all2, all3, all4, all5;
		all3 = new byte[_1M << 2];
		all4 = new byte[_1M << 2];
		all1 = new byte[_1M >> 2];
		all2 = new byte[_1M >> 1];
		all4=null;
		all5 = new byte[_1M << 2];
	}
}
