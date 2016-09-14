package com.eric.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

public class CharTest {
	public static void main(String[] args) {
		char[] ch = new char [100];
		for (int i = 0; i < ch.length; i++) {
			char c = (char) (i);
			System.out.print(c);
		}
		Charset cset = Charset.forName("utf-8");
		Charset cset2 = Charset.forName("gbk");
		Set set = cset.aliases();
		for (Object object : set) {
			System.out.println(object);
		}
		Map<String, Charset> chats = Charset.availableCharsets();
		for (String name : chats.keySet()) {
			System.out.println(name);
		}
		String str = "�Ұ����Ƕ�b����";
		ByteBuffer bb = cset.encode(str);
		
		System.out.println(bb.toString() + "##");
		byte[] ba = bb.array();
		for (int i = 0; i < ba.length; i++) {
			System.out.println(ba[i]);
		}
		ByteBuffer bbuf = ByteBuffer.wrap(ba, 0, ba.length);
		CharBuffer cbuf = cset2.decode(bbuf);
		System.out.println(cbuf.toString());
		
	}
}
