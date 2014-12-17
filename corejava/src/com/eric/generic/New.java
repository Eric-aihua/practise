package com.eric.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * create a utility containing various static methods, which produces the most commonly used implementations of the
 * various containers:
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class New {
    static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }

    static <T> List<T> list() {
        return new ArrayList<T>();
    }
    static void method(List<String> list){
        System.out.println("method");
    }
    static void method2(List list){
        System.out.println("method");
    }

    /*
     * how this is used—type argument inference eliminates the need to repeat the generic parameter list
     */
    public static void main(String[] args) {
        Map<String, Integer> map = map();
        map.put("Eric", 10);
        List<String> list = list();
        list.add("jeson");
        System.out.println(list);
        System.out.println(map);
        /*
         * If you pass the result of a method call such as New.map( ) as an argument to another method, the compiler
         * will not try to perform type inference.  method2's parameter haven' t generic information, that could be accept list() method as a parameter
         */
        //method(New.list());
        method2(New.list());
    }
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
