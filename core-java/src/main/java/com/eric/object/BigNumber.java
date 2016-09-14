package com.eric.object;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigNumber {
	public static void main(String[] args) {
	    String s1=Long.MAX_VALUE+"";
	    String s2=Long.MAX_VALUE+"";
	    String s3="123.1231231231234234234234";
	    String s4="123.1231231231234234234234";
	    
	    System.out.println("Long Max value is:"+s1);
	    System.out.println("Integer Max value is:"+Integer.MAX_VALUE);
	    System.out.println("Double Max value is:"+Double.MAX_VALUE);
	    System.out.println("float Max value is:"+Float.MAX_VALUE+"\n");
	    
	    System.out.println("BigInteger add result:"+bigNumAdd(s1,s2).toString());
	    System.out.println("Long add result:"+bigNumAdd2(s1,s2));
	    System.out.println("BigDecimal add result:"+bigDecimalAdd(s3,s4).toString());
	    System.out.println("floatDecimal add result:"+bigDecimalAdd2(s3,s4));
    }
	
	public static BigInteger bigNumAdd(String num1,String num2){
		BigInteger bi=BigInteger.valueOf(Long.valueOf(num1));
		BigInteger bi2=BigInteger.valueOf(Long.valueOf(num2));
		return bi.add(bi2);
	}
	public static long bigNumAdd2(String num1,String num2){
		long l1=Long.valueOf(num1);
		long l2=Long.valueOf(num2);
		return l1+l2;
	}
	
	public static BigDecimal bigDecimalAdd(String num1,String num2){
		BigDecimal bi=new BigDecimal(num1);
		BigDecimal bi2=new BigDecimal(num2);
		return bi.add(bi2);
	}
	public static float bigDecimalAdd2(String num1,String num2){
		float l1=Float.valueOf(num1);
		float l2=Float.valueOf(num2);
		return l1+l2;
	}
}
