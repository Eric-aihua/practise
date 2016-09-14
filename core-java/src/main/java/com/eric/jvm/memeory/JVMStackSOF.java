package com.eric.jvm.memeory;

public class JVMStackSOF {
	
	/**
	 * (1) 在hotspot虚拟机中不区分虚拟机栈(-Xss)和本地方法栈(-Xoss),且只有对Xss参数的设置,才对栈的分配有影响
	 * 
	 * (2) 虚拟机栈描述的是方法执行的内存模型,每个方法执行都会创建一个栈帧,用于存储局部变量表,操作数栈,动态链接,方法的出口等信息,
	 * 每个栈帧都对应着一个方法被调用的完整过程, 
	 * (3)
	 * 由于StackOverflowError->VirtualMachineError->Error
	 * ->Throwable,所以catch的时候如果用Exception的话将捕获不到异常 Stack length 会随着-Xss的减少而相应的变小
	 */
	private int	stackNumber1	= 1;
	public void stackLeck1() {
		stackNumber1++;
		stackLeck1();
	}
	
	public static void main(String[] args) {
		JVMStackSOF jvmStackSOF = new JVMStackSOF();
		try {
			
			jvmStackSOF.stackLeck1();
		} catch (Throwable ex) {
			System.out.println("Stack length:" + jvmStackSOF.stackNumber1);
			ex.printStackTrace();
		}
	}
	
}
