package com.eric.generic;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * suppose you’d like a special type of list that randomly selects one of its elements each time you call select( ).
 * When doing this you want to build a tool that works with all objects, so you use generics
 * 
 * 
 * 
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class RandomList<T> {
    public static void main(String[] args) {
        //use generic to generator RandomList object
        RandomList<String> rl = new RandomList<String>();
        for (String str : "You can see how the final  specification on the public fields prevents them from being reassigned after construction, in the failure of the statement"
                .split(" ")) {
            rl.addItem(str);
        }
        for (int i = 0; i < rl.size(); i++) {
            System.out.println(rl.select());
        }
    }

    private ArrayList<T> storer = new ArrayList<T>();

    private void addItem(T t) {
        storer.add(t);
    }

    private T select() {
        return storer.get(new Random().nextInt(storer.size()));
    }

    public int size() {
        return storer.size();
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
