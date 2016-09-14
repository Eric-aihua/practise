package com.eric.generic;

/**
 * 
 * Provides the description
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class HoldObject {

    public static void main(String[] args) {
        System.out.println(new Holder<A>(new A()));
        System.out.println(new Holder<B>(new B()));
    }
}

// use generic make Holder could hold variant type of class
class Holder<T> {
    private T obj;

    public Holder(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Holder:" + obj.getClass().getSimpleName();
    }
}

class A {
}

class B {
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
