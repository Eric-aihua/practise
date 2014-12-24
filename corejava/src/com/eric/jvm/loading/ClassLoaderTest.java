/**
 * 
 */
package com.eric.jvm.loading;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用来演示类加载器的作用：除了加载类之外还可以用来作为类的唯一表示的一部分（类加载器+类本省-》确定唯一性） 此程序要在用命令行去执行
 * 
 * @author eric
 * 
 */
public class ClassLoaderTest {

	/**
	 * @param args
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Object obj = getObject("com.eric.jvm.loading.Initialize");
		//Object obj = getObject("com/eric/jvm/loading/Initialize.class");
		 System.out.println(obj);
		//((com.eric.jvm.loading.Initialize)obj).printInfo();
		System.out.println("obj1 instance:" + obj.getClass().getName());
		System.out.println("obj1 is Instance of  com.eric.jvm.loading.Initialize: "
				+ (obj instanceof com.eric.jvm.loading.Initialize));
		System.out.println("obj1 class loader:" + obj.getClass().getClassLoader());
		com.eric.jvm.loading.Initialize obj2 = new com.eric.jvm.loading.Initialize();
		((Initialize)obj2).printInfo();
		System.out.println(obj2);
		System.out.println("obj2 instance of Initialize:" + (obj2 instanceof com.eric.jvm.loading.Initialize));
		System.out.println("obj2 class loader:" + obj2.getClass().getClassLoader());
	}

	private static Object getObject(String classPath) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Class<?> c = getClass(classPath);
		if (c != null) {
			return c.newInstance();
		} else {
			throw new ClassNotFoundException();
		}
	}

	private static Class<?> getClass(String classPath) throws ClassNotFoundException {
		ClassLoader cl = new ClassLoader() {
			@Override
			public Class<?> loadClass(String classPath) throws ClassNotFoundException {
				String fileName = null;
				try {
					fileName = classPath.substring(classPath.lastIndexOf('.') + 1);
					//fileName=classPath;
					System.out.println("fileName:" + fileName);
					if (fileName == null || fileName.trim().equals("")) {
						throw new RuntimeException("Class name is invalidate");
					}
					InputStream is = getResourceAsStream(fileName);
					if (is == null) {
						System.out.println("use Super class loader to loading");
						return super.loadClass(fileName);
					}
					byte[] bs = new byte[is.available()];
					is.read(bs);
					return defineClass(fileName, bs, 0, bs.length);
				} catch (IOException e) {
					throw new ClassNotFoundException(fileName);
				}
			}
		};
		return cl.loadClass(classPath);
	}

}
