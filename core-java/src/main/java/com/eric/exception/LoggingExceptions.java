package com.eric.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

class LoggingException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2409227171422069149L;
	private static Logger logger=Logger.getLogger("LoggingException");
	public LoggingException() { 
	    StringWriter trace = new StringWriter(); 
	    printStackTrace(new PrintWriter(trace)); 
	    logger.severe(trace.toString()); 
	  }
}

public class LoggingExceptions {
	public static void main(String[] args) {
		try{
			throw new LoggingException();
		}catch(LoggingException ex){
			//ex.printStackTrace();
			System.err.print(ex);
		}
	}
}
