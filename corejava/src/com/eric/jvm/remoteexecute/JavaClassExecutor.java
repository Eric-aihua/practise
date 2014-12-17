package com.eric.jvm.remoteexecute;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaClassExecutor {
	private String classPath;

	public JavaClassExecutor(String classPath) {
		this.classPath = classPath;
	}

	// /home/eric/sourcecode/Source/bin/com/eric/jvm/remoteexecute/ClassModifier.class
	public String executorSwap() throws Exception {
		FileInputStream fis = new FileInputStream(classPath);
		int classSize = fis.available();
		byte[] classBytes = new byte[classSize];
		fis.read(classBytes);
		fis.close();
		return executor(classBytes);
	}

	private String executor(byte[] bytes) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		byte[] newClassBytes = new ClassModifier(bytes).modifyUTF8info("java/lang/System",
				"com/eric/jvm/remoteexecute/HackSystem");
		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class originalClass = loader.loadByte(newClassBytes);
		Method method = originalClass.getMethod("main", new Class[] { String[].class });
		method.invoke(null, new String[] { null });
		return HackSystem.getBufferString();
	}

	public void generateNewClass(byte[] bytes) {

	}
}
