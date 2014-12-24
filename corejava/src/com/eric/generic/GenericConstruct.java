package com.eric.generic;

public class GenericConstruct {

    /**
     * generic of construct and method
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new Constrtor<String, Integer>("Eric", 10));
        System.out.println(new Constrtor<String, String>("Eric", ""));
        // System.out.println(new Constrtor<String>("Eric"));
    }
}

class Constrtor<T, V> {
    private T t;
    private V v;

    // public <M> Constrtor(M m) {
    //
    // }
    public Constrtor(T t, V v) {
        this.t = t;
        this.v = v;
    }

    public <K> void method() {

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
