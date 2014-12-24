package com.eric.jvm.remoteexecute;

/**
 * 通过把指定文件的class文件中的java.lang.System
 * 替换位com.eric.jvm.remoteexecute.HackSystem，以及配合自定义classloader的使用
 * ，把ForTest中通过System.out.println的内容重定向到了在HackSystem里自定义的文件里
 * 
 * @author eric
 * 
 */
public class RemoteExecutor {
	public static void main(String args[]) {
		try {
			String result = new JavaClassExecutor(
					"/home/eric/sourcecode/Source/bin/com/eric/jvm/remoteexecute/ForTest.class")
					.executorSwap();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
