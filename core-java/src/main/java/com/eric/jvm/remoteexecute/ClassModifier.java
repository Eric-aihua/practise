package com.eric.jvm.remoteexecute;


public class ClassModifier {
	public static final int CONSTANT_POOL_COUNT_INDEX = 8;
	public static final int CONSTANT_UTF8_INFO = 1;
	public static final int U1 = 1;
	public static final int U2 = 2;
	// 一共由13种类型的常量,且不包括constant_utf8_info,而且constant_integer_info的tag是3
	public static final int[] CONSTANT_ITEM_LENGTH = { 0, 0, 0, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5, };

	private byte[] classBytes;

	public ClassModifier(byte[] classBytes) {
		this.classBytes = classBytes;
	}

	public byte[] modifyUTF8info(String originalStr, String newStr) {
		int constantCount = getConstantCount(classBytes);
		// 跳过constant count
		int offset = CONSTANT_POOL_COUNT_INDEX + U2;
		// 常量表是从一真正开始使用
		for (int i = 1; i < constantCount; i++) {
			int constantTag = ByteUtils.convertBytesToInt(classBytes, offset, U1);
			if (constantTag == CONSTANT_UTF8_INFO) {
				// skip tag
				offset += U1;
				// 提取constant_utf8_info的长度
				int utf8Length = ByteUtils.convertBytesToInt(classBytes, offset, U2);
				// System.out.println(utf8Length);
				if (utf8Length > 0) {
					// skip length
					offset += U2;
					String utf8Info = ByteUtils
							.convertBytesToString(classBytes, offset, utf8Length);
					//System.out.println(utf8Info);
					// skip length of string
					if (utf8Info.equals(originalStr)) {
						//如果是要替换的字符串，先替换字符串的length
						byte[] newStrLen=ByteUtils.convertIntToBytes(newStr.length(),U2);
						classBytes=ByteUtils.replaceBytesByNewBytes(classBytes,offset-U2,newStrLen,U2);
						//再替换内容
						classBytes = ByteUtils.replaceBytesByNewString(classBytes, originalStr,
								newStr, offset);
						offset += newStr.length();
					} else {
						offset += utf8Length;
					}
				} else {
					offset += U2;
				}
			} else {
				offset += CONSTANT_ITEM_LENGTH[constantTag];
			}
		}

		return classBytes;

	}

	// get constant item count from class bytes
	private int getConstantCount(byte[] classBytes) {
		return ByteUtils.convertBytesToInt(classBytes, CONSTANT_POOL_COUNT_INDEX, U2);
	}

	public byte[] getClassBytes() {
		return classBytes;
	}

	public void setClassBytes(byte[] classBytes) {
		this.classBytes = classBytes;
	}

}