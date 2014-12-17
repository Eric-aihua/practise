package com.eric.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class AddMethod {
	public static void main(String[] args) {
		List<String> list=new LinkedList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		ListIterator lit=list.listIterator();
		
		List<String> list2=new LinkedList<String>();
		list2.add("d");
		list2.add("e");
		list2.add("f");
		list2.add("g");
		list2.add("h");
		ListIterator lit2=list2.listIterator();
		
		while(lit2.hasNext()){
			if(lit.hasNext()){
				lit.next();
			}
			lit.add(lit2.next());
		}
		System.out.println(lit2.nextIndex());
		for (String string : list) {
			System.out.println(string);
		}
		lit2=list2.listIterator();
		 while(lit2.hasNext()){
			 lit2.next();
			 lit2.remove();
		 }
		 System.out.println("list2:"+list2);
		 
		 list.removeAll(list2);
		 System.out.println("list:"+list);
	}
}
