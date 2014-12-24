package com.eric.init;

public class ReturnSelfTest {
	public static void main(String[] args) {
	    new Test().returnSelf().returnSelf().returnSelf().returnSelf().printf();
    }
}

class Test {
	int	i	= 1;
	
	Test returnSelf(){
		this.i++;
		return this;
	}
	void printf() {
		System.out.println(i);
	}
}
