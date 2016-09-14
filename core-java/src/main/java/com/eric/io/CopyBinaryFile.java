package com.eric.io;

import com.eric.reflect.ExecuteTimerHandler;

/*
 * 缓冲数组和,非缓冲租拷贝二进制文件是的效率对比
 * 
 * copyFile was excute 0 times
 execute copyFile spend 22656 million sencond!
 doCopyFile was excute 0 times
 Copy Successful::E:\sourcecode\corejava\src\com\eric\io\test3.avi
 execute doCopyFile spend 1892 million sencond!
 * */
public class CopyBinaryFile {
	public static void main(String[] args) throws Exception {
		IReadFileTools tools = (IReadFileTools) ExecuteTimerHandler.newInstance(new ReadFileTools());
		tools.copyFile("E:\\sourcecode\\corejava\\src\\com\\eric\\io\\test.avi", "E:\\sourcecode\\corejava\\src\\com\\eric\\io\\test2.avi");
		tools.doCopyFile("E:\\sourcecode\\corejava\\src\\com\\eric\\io\\test.avi", "E:\\sourcecode\\corejava\\src\\com\\eric\\io\\test3.avi");
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
