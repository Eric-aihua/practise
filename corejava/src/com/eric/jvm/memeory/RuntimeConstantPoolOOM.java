package com.eric.jvm.memeory;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {
	
	/**
	 * 本程序主要用来掩饰如果让常量区溢出,因为常量去属于方法区的一部分,所以最终报的错误是......OutOfMemoryError:PerGen
	 * space....
	 * 
	 * 主要参数:-XX:PermSize=10m -XX:MaxPermSize=10m     ------->number is:93712
	 *              -XX:PermSize=10m -XX:MaxPermSize=10m     -------->number is 224784
	 * 
	 */
	
	public void generateOOM() {
		List<String> list = new ArrayList<String>();
		int num = 0;
		String i = num + "";
		try {
			while (true) {
				list.add(String.valueOf((num++) + "").intern());
			}
		} catch (Throwable th) {
			System.out.println(num);
			th.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new RuntimeConstantPoolOOM().generateOOM();
	}
	
}
