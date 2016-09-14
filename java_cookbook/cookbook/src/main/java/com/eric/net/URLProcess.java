package com.eric.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author dell
 *
 */
public class URLProcess {
	
	public static String encodeUrl(String url){
		String result=null;
		try {
			result=URLEncoder.encode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String decodeUrl(String url){
		String result=null;
		try {
			result= URLDecoder.decode(url,"gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
//		try {
//			System.out.println(new String("http://news.baidu.com/ns?word=0�+��+NW&tn=news&from=news&cl=2&rn=20&ct=0".getBytes("gb2312"),"gb2312"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(decodeUrl("http://news.baidu.com/ns?word=0�+��+NW&tn=news&from=news&cl=2&rn=20&ct=0"));
	}
	

}
