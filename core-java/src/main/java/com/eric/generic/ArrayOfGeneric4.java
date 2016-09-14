package com.eric.generic;

import java.lang.reflect.Array;

/**
 * 
 * The type token Class<T> is passed into the constructor in order to recover from the erasure, so that we can create
 * the actual type of array that we need, although the warning from the cast must be suppressed with @SuppressWarnings.
 * Once we do get the actual type, we can return it and get the desired results, as you see in main( ). The runtime type
 * of the array is the exact type T[].
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class ArrayOfGeneric4<T> {

    T[] ts;

    public ArrayOfGeneric4(Class<T> type, int size) {
        /* to solution array of generic key code! */
        ts = (T[]) Array.newInstance(type, size);
    }

    public T get(int index) {
        return ts[index];
    }

    public T[] rep() {
        return ts;
    }

    public void set(int index, T t) {
        ts[index] = t;
    }

    public static void main(String[] args) {
        ArrayOfGeneric4<Integer> aog2 = new ArrayOfGeneric4<Integer>(Integer.class, 10);
        Object[] objs = aog2.rep();
        for (int i = 0; i < 10; i++) {
            aog2.set(i, i);
            System.out.println(aog2.get(i));
        }
        try {
            Integer[] strs = aog2.rep();
            System.out.println("user Array.newInstance to create generci of array was successful!!!!! ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
