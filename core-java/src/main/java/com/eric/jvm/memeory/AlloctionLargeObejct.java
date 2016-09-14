package com.eric.jvm.memeory;

public class AlloctionLargeObejct {

	/**验证大对象直接进入老年去
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
	 * -XX:+PrintGCDetails
	 * -XX:PretenureSizeThreshold=1048576
	 */
	public static final int _1M = 1024 << 10;

	public static void main(String[] args) {
		testAllolaction();
	}

	public static void testAllolaction() {
		byte[] all1, all2, all3, all4;
		all1 = new byte[_1M];
	}

}
