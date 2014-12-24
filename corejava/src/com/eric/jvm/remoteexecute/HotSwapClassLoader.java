package com.eric.jvm.remoteexecute;

public class HotSwapClassLoader extends ClassLoader {
	public HotSwapClassLoader() {
		super(HotSwapClassLoader.class.getClassLoader());
	}

	public Class<?> loadByte(byte[] bytes) {
		return defineClass(null, bytes, 0, bytes.length);
	}
}
