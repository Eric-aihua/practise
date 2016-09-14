package com.eric.reflect;

import java.util.ArrayList;
import java.util.List;

class CountInteger {
    private static long index;
    private final long id = index;
    {
        index++;
    }

    @Override
    public String toString() {
        return "id:" + id;
    }
}



public class FilledList<T> {
    private Class<T> type;

    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> createList(int count) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < count; i++) {
            try {
                list.add(type.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new FilledList<CountInteger>(CountInteger.class).createList(10));
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
