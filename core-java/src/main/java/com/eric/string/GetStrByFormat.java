package com.eric.string;

import java.util.HashMap;
import java.util.Map;

public class GetStrByFormat {
	protected static final String LEFT="{";
	protected static final String RIGHT="}";
	public static void main(String[] args) {
		
		Map<String,String> params=initParam();
		String format="M{PAN}D{Expire}{Member}";
		String value="M123456D22112";
		getValueByFormat(format,value,params);	    
    }
	protected static void getValueByFormat(String format,String value,Map<String,String> params){
		String[] rights=format.split("RIGHT");
		for (int i = 0; i < rights.length; i++) {
	        System.out.println(rights[i]);
        }
	}
	public static Map<String,String> initParam(){
		Map<String,String> params=new HashMap<String,String>();
		params.put("PAN_len","1,19");
		params.put("Expire_len", "4");
		params.put("Member_len", "1");
		params.put("Expire_op", "false");
		params.put("Member_op", "true");
		return params;
	}
}
