package com.eric.interfaceAndInnerClass;

/**
 * Apply 类主要处理Process类型的方法，在下面的UperStringProcess，LowerStringProcess
 * 由于实现了Process的借口所以理所当然的会被Apply的处理
 * NumberOperator借口的子类由于并没有显示process借口,所以其子类是不能被Apply方法所调用，而通常情况下类似于NumberOperator这样的类往往是库函数定义好的，我们也不能去更改，
 * 这个时候如果想让其被Apply类处理的话，Adapter模式就发挥其作用了，具体请康NumberAdapter类
 * 
 * */
public class AdapterPartner {
	public static void main(String[] args) {
		String str = "I Come From Chinses";
		Process uperStr = new UperStringProcess();
		Process lowStr = new LowerStringProcess();
		Apply.Process(uperStr, str);
		Apply.Process(lowStr, str);
		
		NumberOperator noAdd = new AddNumber();
		NumberOperator noSub = new SubNumber();
		
		NumberAdapter naAdd = new NumberAdapter(noAdd);
		NumberAdapter naSub = new NumberAdapter(noSub);
		
		Apply.Process(naAdd, "numer add:");
		Apply.Process(naSub, "numer sub:");
		
	}
}

interface Process {
	String name();
	
	void process(Object obj);
}

class Apply {
	static void Process(Process process, Object obj) {
		System.out.println("Class name is:" + process.name());
		process.process(obj);
	}
}

abstract class StringProcess implements Process {
	public String name() {
		return this.getClass().getName();
	}
	
	public abstract void process(Object obj);
}

class UperStringProcess extends StringProcess {
	@Override
	public void process(Object obj) {
		System.out.println("to uper case:" + ((String) obj).toUpperCase());
	}
}

class LowerStringProcess extends StringProcess {
	@Override
	public void process(Object obj) {
		System.out.println("to lower case:" + ((String) obj).toLowerCase());
	}
}

class NumberAdapter implements Process{
	private NumberOperator no;
	public NumberAdapter(NumberOperator no){
		this.no=no;
	}

	public String name() {
	    return no.name();
    }

	public void process(Object obj) {
	    no.process(obj);
    }
	
}

interface NumberOperator {
	int	numbe1	= 10;
	int	number2	= 5;
	
	String name();
	
	void process(Object obj);
}

class AddNumber implements NumberOperator {
	public String name() {
		return "two number added!";
	}
	
	public void process(Object obj) {
		System.out.println(obj);
		System.out.println(numbe1 + number2);
	}
}

class SubNumber implements NumberOperator {
	public String name() {
		return "two number sub!";
	}
	
	public void process(Object obj) {
		System.out.println(obj);
		System.out.println(numbe1 - number2);
	}
}
