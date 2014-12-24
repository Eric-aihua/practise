package com.eric.reflect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class SimpleProxyDemo {
	public static final String	PCE_VERSION_CONTROL	= "@(#) $RCSfile: $, $Revision: $, $Date: $";
	
	static void doing(Do doImpl) {
		doImpl.read();
		doImpl.write();
	}
	
	public static void main(String[] args) {
		doing(new DoProxyImp(new DoImpl()));
	}
}

/**
 * interface must be public
 * */
interface Do {
	public void read();
	
	public void write();
}

/**
 * implement Class
 * */
class DoImpl implements Do {
	public void read() {
		System.out.println("read()");
	}
	
	public void write() {
		System.out.println("write()");
		
	}
}

/**
 * Class Proxy
 * */
class DoProxyImp implements Do {
	private static final File	  file	= new File("C:/MKS/PCE-AP/dev/PersonUtil/src/com/eric/reflect/log.txt");
	private static BufferedWriter	bw;
	
	private Do	                  doImp;
	
	public DoProxyImp(Do doImpl) {
		this.doImp = doImpl;
	}
	
	public DoProxyImp() {};
	
	public void read() {
		writeLog("Read method was execute! " + new Date() + "\n");
		doImp.read();
	}
	
	public void write() {
		writeLog("write method was execute! " + new Date() + "\n");
		doImp.write();
	}
	
	private static void writeLog(String str) {
		try {
			if (bw == null) {
				bw = new BufferedWriter(new FileWriter(file));
			}
			bw.append(str);
			bw.flush();
			// bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
