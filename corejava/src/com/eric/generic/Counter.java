package com.eric.generic;

public class Counter {
    private static long counter = 0;
    private final long id = ++counter;

    @Override
    public String toString() {
        return String.valueOf(id);
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
