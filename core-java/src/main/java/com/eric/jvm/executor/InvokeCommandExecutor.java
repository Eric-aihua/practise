
package com.eric.jvm.executor;

/**
 * 通过反编译字节码来验证 
 * invokestatic:调用静态方法 
 * invokespecial:调用构造器方法,私有方法以及父类方法
 * invokevirtual:调用虚方法以及final方法(虽然用invokevirtual调用,可是因为final方法的不可覆盖性,因此也是非虚方法)
 * invokeinterface:调用接口方法,会在运行时再确定一个具体的实现方法
 * 
 * 
 * javap -verbose com.eric.jvm.executor.InvokeCommandExecutor
 * 
 * @author Eric
 * 
 */
public class InvokeCommandExecutor {
	
	
	public static void main(String[] args) {
		//invokestatic
		SubInvoker.invokeStatic();
		//invokespecial,
		SubInvoker si=new SubInvoker();
		//invokevirtual
		si.invokeVirtual();
		//invokeinterface
		si.invokeInterface();
	}
	
}

class SubInvoker implements IExecutor{
	public static void invokeStatic(){
		System.out.println("invokestatic was execute");
	}
	public SubInvoker(){
		System.out.println("invokespecial was execute in construct");
	};
	public void invokeVirtual(){
		System.out.println("invokevirtual was execute");
	}
	@Override
    public void invokeInterface() {
		System.out.println("invokeinterface was execute");
    }
}

interface IExecutor{
	public void invokeInterface();
}
