package com.eric.io;

import java.io.IOException;

/*
 * IReadFileTools must be public for proxy class
 * */
public interface IReadFileTools {
	public String readByBufferedInputStreamNoArray(String file);
	
	public String readByBufferReader(String file);
	
	public void readByChannel(String file) throws Exception;
	
	public void readByChannelMap(String file) throws Exception;
	
	public void readByDataInputStream(String file) throws Exception;
	
	public void readByBufferedInputStream(String file) throws Exception;
	
	public void copyFile(String source, String dest) throws Exception;
	
	public void storingAndRecoveringData(String file) throws Exception;
	
	public void doCopyFile(String src, String dest) throws IOException;
	
	public void copyFileByChannel(String file, String file2) throws Exception;
	
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
