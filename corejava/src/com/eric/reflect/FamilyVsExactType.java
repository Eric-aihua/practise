package com.eric.reflect;

class Base {
    public String toString() {
        return "Base";
    }
}

class Derived extends Base {
    public String toString() {
        return "Derived";
    }
}

public class FamilyVsExactType {

    static void comparison(Object obj) {
        System.out.println(obj + " instanceof Base " + (obj instanceof Base));
        System.out.println(obj + " instanceof Derived " + (obj instanceof Derived));
        System.out.println("Base.class.isInstance(" + obj + ") " + (Base.class.isInstance(obj)));
        System.out.println("Derived.class.isInstance(" + obj + ") " + (Derived.class.isInstance(obj)));
        System.out.println("Base.class==" + obj + ".getClass() " + (Base.class == obj.getClass()));
        System.out.println("Derived==" + obj + ".getClass() " + (Derived.class == obj.getClass()));
        System.out.println("Base.class.equals(" + obj + ".getClass()) " + (Base.class.equals(obj.getClass())));
        System.out.println("Derived.equals(" + obj + ".getClass() )" + (Derived.class.equals(obj.getClass())));
        System.out.println();

    }

    public static void main(String[] args) {
        comparison(new Base());
        comparison(new Derived());
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
