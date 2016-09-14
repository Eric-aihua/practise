package com.eric.jvm.memeory;

public class JVMStackOOM {
	
	/**
	 * (1)不停的创建线程,因为OS提供给每个进程的内存是有限的,且虚拟机栈+本地方法栈=(总内存-最大堆容量(X模型)-最大方法区容量(
	 * MaxPermSize)),于是可以推断出,当每个线程的栈越大时,那么可以分配的线程数量的就越少,当没有足够的内存来分配线程所需要的栈空间时,
	 * 就会抛出OutOfMemoryException
	 * (2)由于在window平台的虚拟机中,java的线程是隐射到操作系统的内核线程上的,所以运行一下代码时,会导致操作系统假死(我就尝到了血的代价)
	 */
	private static volatile int	threadNumber	= 0;
	
	public void stackLeakByThread() {
		while (true) {
			new Thread() {
				public void run() {
					threadNumber++;
					while (true) {
						System.out.println(Thread.currentThread());
					}
				}
			}.start();
		}
	}
	
	public static void main(String[] args) {
		JVMStackOOM jvmStackOOM = new JVMStackOOM();
		try {
			jvmStackOOM.stackLeakByThread();
		} catch (Throwable ex) {
			System.out.println(JVMStackOOM.threadNumber);
			ex.printStackTrace();
		}
	}
}
