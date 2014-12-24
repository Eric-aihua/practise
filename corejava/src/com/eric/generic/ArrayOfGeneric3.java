package com.eric.generic;

/**
 * 
 * Initially, this doesn’t look very different compare with ArrayOfGeneric2.java , just that the cast has been moved.
 * Without the ©SuppressWarnings annotations, you will still get "unchecked" warnings. However, the internal
 * representation is now Object[] rather than T[]. When get( ) is called, it casts the object to T, which is in fact the
 * correct type, so that is safe. However, if you call rep( ) , it again attempts to cast the Object[] to a T[], which
 * is still incorrect, and produces a warning at compile time and an exception at run time. Thus there’s no way to
 * subvert the type of the underlying array, which can only be Object[]. The advantage of treating array internally as
 * Object[] instead of T[] is that it’s less likely that you’ll forget the runtime type of the array and accidentally
 * introduce a bug (although the majority, and perhaps all, of such bugs would be rapidly detected at run time)
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class ArrayOfGeneric3<T> {
    Object[] ts;

    public ArrayOfGeneric3(int size) {
        ts = new Object[size];
    }

    public T get(int index) {
        return (T) ts[index];
    }

    public T[] rep() {
        return (T[]) ts;
    }

    public void set(int index, T t) {
        ts[index] = t;
    }

    public static void main(String[] args) {
        ArrayOfGeneric3<Integer> aog2 = new ArrayOfGeneric3<Integer>(10);
        Object[] objs = aog2.rep();
        for (int i = 0; i < 10; i++) {
            aog2.set(i, i);
            System.out.println(aog2.get(i));
        }
            Integer[] strs = aog2.rep();
            System.out.println(strs);
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
