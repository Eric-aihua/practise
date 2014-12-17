package com.eric.generic;

public class GenericMethodTest {
    public static <T> void run(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        run("sun");
        run(5);

        Object[] objs = { "String", 123 };
        for (int i = 0; i < objs.length; i++) {
            Object object = objs[i];
            System.out.println(object);

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
