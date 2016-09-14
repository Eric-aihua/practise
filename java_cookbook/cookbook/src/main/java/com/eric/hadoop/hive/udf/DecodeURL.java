package com.eric.hadoop.hive.udf;

import java.net.URLDecoder;
import org.apache.hadoop.hive.ql.exec.UDF;

public class DecodeURL extends UDF {
	private String url = null;
	private int times = 2;
	private String code = "gb2312";

	public DecodeURL() {
	}

	public String evaluate(String urlStr, String srcCode, int count) {
		if (urlStr == null) {
			return null;
		}
		if (count <= 0) {
			return urlStr;
		}
		if (srcCode != null) {
			code = srcCode;
		}
		url = urlStr;
		times = count;
		for (int i = 0; i < times; i++) {
			url = decoder(url, code);
		}
		return url;
	}

	public String evaluate(String urlStr, String srcCode) {
		if (urlStr == null) {
			return null;
		}
		url = urlStr;
		code = srcCode;
		return evaluate(url, code, times);
	}

	public String evaluate(String urlStr, int count) {
		if (urlStr == null) {
			return null;
		}
		if (count <= 0) {
			return urlStr;
		}
		url = urlStr;
		times = count;

		return evaluate(url, code, times);
	}

	public String evaluate(String urlStr) {
		if (urlStr == null) {
			return null;
		}
		url = urlStr;
		return evaluate(url, code, times);
	}

	private String decoder(String urlStr, String code) {
		if (urlStr == null || code == null) {
			return null;
		}
		try {
			urlStr = URLDecoder.decode(urlStr, code);
		} catch (Exception e) {
			return null;
		}
		return urlStr;
	}
	
	public static void main(String[] args) {
		System.out.println(new DecodeURL().evaluate("http://news.baidu.com/ns?word=0�+��+NW&tn=news&from=news&cl=2&rn=20&ct=0"));
	}
}
