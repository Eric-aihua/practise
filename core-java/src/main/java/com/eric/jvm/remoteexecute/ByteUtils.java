package com.eric.jvm.remoteexecute;

import java.util.Arrays;

public class ByteUtils {

	// get integer from bytes by start and length
	public static int convertBytesToInt(byte[] bytes, int start, int len) {
		byte[] tempBytes = new byte[len];
		System.arraycopy(bytes, start, tempBytes, 0, len);
		int sum = 0;
		for (int i = 0; i < tempBytes.length; i++) {
			int num = (tempBytes[i] << (--len * 8)) & 0xff;
			sum += num;
		}
		return sum;
	}

	// get String from bytes by start and length
	public static String convertBytesToString(byte[] classBytes, int start, int len) {
		byte[] tempBytes = new byte[len];
		System.arraycopy(classBytes, start, tempBytes, 0, len);
		return new String(tempBytes);
	}

	public static byte[] replaceBytesByNewString(byte[] bytes, String originalStr, String newStr, int offset) {
		byte[] newBytes = new byte[bytes.length - (originalStr.length() - newStr.length())];
		System.arraycopy(bytes, 0, newBytes, 0, offset);
		System.arraycopy(newStr.getBytes(), 0, newBytes, offset, newStr.length());
		System.arraycopy(bytes, offset + originalStr.length(), newBytes, offset + newStr.length(), bytes.length
				- offset - originalStr.length());
		return newBytes;
	}

	public static byte[] convertIntToBytes(int number, int length) {
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			bytes[length - i - 1] = (byte) ((number >> 8 * i) & 0xff);
		}
		return bytes;
	}

	public static byte[] replaceBytesByNewBytes(byte[] sourceBytes, int start, byte[] newBytes, int len) {
		byte[] newSourceBytes = new byte[sourceBytes.length + (newBytes.length - len)];
		System.arraycopy(sourceBytes, 0, newSourceBytes, 0, start);
		System.arraycopy(newBytes, 0, newSourceBytes, start, len);
		System.arraycopy(sourceBytes, start + len, newSourceBytes, start + len, sourceBytes.length - start
				- len);
		return newSourceBytes;
	}

	public static void main(String args[]) {
		int a = 45;
		byte[] bytes = convertIntToBytes(45, 2);
		for (byte b : bytes) {
			System.out.println(b);
		}
		for(byte b:"java/lang/System".getBytes()){
			System.out.print(b+", ");
		}
		System.out.println();
		for(byte b:"com/eric/jvm/remoteexecute/HackSystem".getBytes()){
			System.out.print(b+", ");
		}
		
	}

}
