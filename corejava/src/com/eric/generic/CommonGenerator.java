package com.eric.generic;

/**
 * Here’s a class that produces a Generator for any class that has a default constructor.
 * */
public class CommonGenerator<T> implements Generator<T> {
    private Class<T> type;

    public CommonGenerator() {
    }

    public CommonGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new CommonGenerator<Counter>(Counter.class).next());
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
