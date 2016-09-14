package com.eric.generic;

/**
 * 
 *compare with SimpleHolder.java, since get( ) and set( ) produce the same bytecodes, all the action in generics happens at the boundaries—the extra
 * compile-time check for incoming values, and the inserted cast for outgoing values. It helps to counter the confusion
 * of erasure to remember that "the boundaries are where the action takes place."
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class GenericHolder<T> {
    T t;

    /**
     * @return the t
     */
    public T getT() {
        return t;
    }

    /**
     * @param t
     *            the t to set
     */
    public void setT(T t) {
        this.t = t;
    }

    public GenericHolder(T t) {
        super();
        this.t = t;
    }

    public GenericHolder() {
        super();
    }

    public static void main(String[] args) {
        GenericHolder<String> sh = new GenericHolder<String>();
        sh.setT("Simon");
        String str = sh.getT();
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
