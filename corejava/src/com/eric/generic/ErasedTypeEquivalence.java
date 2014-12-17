package com.eric.generic;

import java.util.ArrayList;

public class ErasedTypeEquivalence {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    /**
     * you can say ArrayList.class, you cannot say ArrayList<Integer>.class (2)Array List < String > and Array List <
     * Integer > could easily be argued to be distinct types
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(ArrayList.class);
        // System.out.println(ArrayList<String>.class);
        Class<?> class1 = new ArrayList<String>().getClass();
        Class<?> class2 = new ArrayList<Integer>().getClass();
        System.out.println("class1:" + class1 + " class2:" + class2);
        System.out.println(class1 == class2);
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
