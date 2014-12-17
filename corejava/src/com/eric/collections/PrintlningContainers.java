package com.eric.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PrintlningContainers {
	static Collection <String>fill(Collection<String> collection) {
		collection.add(collection.getClass().getSimpleName()+":");
		collection.add("ONE");
		collection.add("TWO");
		collection.add("THREE");
		collection.add("FOUR");
		return collection;
	}
	
	static Map<String,String> fill(Map<String, String> map) {
		map.put("rat", "Fuzzy");
		map.put("cat", "Rags");
		map.put("dog", "Bosco");
		map.put("dog", "Spot");
		return map;
	}
	
	static void insertEffective(List<Integer> list){
		long begin=System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
	        list.add(i);
        }
		System.out.println(list.getClass().getSimpleName()+" add "+5000000+" elements spend time is:  "+(System.currentTimeMillis()-begin));
	}
	
	public static void main(String[] args) {
//		out.println(fill(new ArrayList<String>()));//存储的顺序就是插入的顺序
//		out.println(fill(new LinkedList<String>()));//存储的顺序就是插入的顺序
//		out.println(fill(new HashSet<String>()));//按照特殊的算法去存取
//		out.println(fill(new TreeSet<String>()));//按照item的顺序就行存储
//		out.println(fill(new LinkedHashSet<String>()));
//		out.println(fill(new HashMap<String, String>()));
//		out.println(fill(new TreeMap<String, String>()));
//		out.println(fill(new LinkedHashMap<String, String>()));
		
		insertEffective(new LinkedList<Integer>());
		insertEffective(new ArrayList<Integer>());
		
	}
}