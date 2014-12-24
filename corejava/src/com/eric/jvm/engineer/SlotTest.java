package com.eric.jvm.engineer;

public class SlotTest {
	/**
	 * 主要验证重复利用Slot对于垃圾回收的帮助 （1）运行参数：-verbose:gc -XX:+PrintGCDetails
	 * （2）64M的对象大于了目前年轻代的空间，根据大对象直接进入老年代的原则，在观察结果的时候需要关注ParOldGen
	 * */

	public static int M = 1024 << 10;

	public static void main(String[] args) {
		new SlotTest().test2();
	}

	/*
	 * replace 在执行gc操作的时候还没有超过它的作用域，也就是堆中还有实例和它直接关联所以不会被回收掉
	 * 
	 * [GC [PSYoungGen: 614K->352K(17856K)] 66150K->65888K(124224K),
	 * 0.0024710 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] [Full GC
	 * (System) [PSYoungGen: 352K->0K(17856K)] [ParOldGen:
	 * 65536K->65759K(106368K)] 65888K->65759K(124224K) [PSPermGen:
	 * 2403K->2401K(21248K)], 0.0102720 secs] [Times: user=0.02 sys=0.00,
	 * real=0.01 secs]
	 */
	public void test1() {
		// 64M
		byte[] replace = new byte[M << 6];
		System.gc();
	}

	/*
	 * 在执行gc时，虽然replace已经过期，但是由于它的Slot中仍然存有相关的局部变量信息，所以gc 还是不可以 对64M的内存进行回收
	 * 
	 * [GC [PSYoungGen: 614K->288K(17856K)] 66150K->65824K(124224K),
	 * 0.0019600 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] [Full GC
	 * (System) [PSYoungGen: 288K->0K(17856K)] [ParOldGen:
	 * 65536K->65758K(106368K)] 65824K->65758K(124224K) [PSPermGen:
	 * 2403K->2401K(21248K)], 0.0139210 secs] [Times: user=0.02 sys=0.00,
	 * real=0.01 secs]
	 */
	public void test2() {
		{
			byte[] replace = new byte[M << 6];
		}
		System.gc();
	}

	/*
	 * 在执行gc之前，由于a复用了replace 的Slot，所以此时可以认为replace在堆中的实例没有相关的引用，因此在gc的时候会将它回收
	 * 
	 * [GC [PSYoungGen: 614K->368K(17856K)] 66150K->65904K(124224K),
	 * 0.0019430 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] [Full GC
	 * (System) [PSYoungGen: 368K->0K(17856K)] [ParOldGen:
	 * 65536K->223K(106368K)] 65904K->223K(124224K) [PSPermGen:
	 * 2403K->2401K(21248K)], 0.0107030 secs] [Times: user=0.01 sys=0.01,
	 * real=0.01 secs]
	 */
	public void test3() {
		{
			byte[] replace = new byte[M << 6];
		}
		int a = 0;
		System.gc();
	}
	
	public void test4(){
//		int a;
//		System.out.println(a);
	}

}
