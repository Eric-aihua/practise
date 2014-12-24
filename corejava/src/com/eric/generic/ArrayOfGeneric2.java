package com.eric.generic;

/**
 * 
 * Because of erasure, the runtime type of the array can only be Object[]. If we immediately cast it to T[], then at
 * compile time the actual type of the array is lost, and the compiler may miss out on some potential error checks.
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class ArrayOfGeneric2<T> {
    public T[] ts;

    public ArrayOfGeneric2(int size) {
        ts = (T[]) new Object[size];
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
        ArrayOfGeneric2<String> aog2 = new ArrayOfGeneric2<String>(10);
        Object[] objs = aog2.rep();
        System.out.println(objs);
        /* will throw ClassCastException */
       // String[] strs = aog2.rep();
        // System.out.println(strs);
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
