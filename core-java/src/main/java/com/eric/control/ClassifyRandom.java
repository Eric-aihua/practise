package com.eric.control;

import java.util.HashMap;
import java.util.Map;

public class ClassifyRandom {
	public static void main(String[] args) {
		randomByarrange();
    }
	static void  randomByarrange(){
		Map lessMap=new HashMap();
		Map equalMap=new HashMap();
		Map largeMap=new HashMap();
		for (int i = 0; i <25; i++) {
	        double d1=Math.random();
	        double d2=Math.random();
	        if(d1>d2){
	        	largeMap.put(i, d1);
	        	
	        }
	        if(d1<d2){
	        	lessMap.put(i, d1);
	        }
	        else{
	        	equalMap.put(i, d1);
	        }
	        	
	        
        }
		System.out.println(lessMap);
		System.out.println(equalMap);
		System.out.println(largeMap);
	}
	static void voidMethod(){
		return ;
	}
}
