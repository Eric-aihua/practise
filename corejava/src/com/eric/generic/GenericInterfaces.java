package com.eric.generic;

import java.util.Iterator;
import java.util.Random;

/**
 * 
 * Generics also work with interfaces. For example, a generator is a class that creates objects. It’s actually a
 * specialization of the Factory Method design pattern, but when you ask a generator for new object, you don’t pass it
 * an y arguments, whereas you typically do pass arguments to a Factory Method. The generator knows how to create new
 * objects without any extra information.
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */

public class GenericInterfaces {

    /**
     * CoffeeGenerator implements the Iterable interface, so it can be used in a foreach statement.
     * 
     * @param args
     */
    public static void main(String[] args) {
        for (Coffee coffee : new CoffeeGenerator<Coffee>(10)) {
            System.out.println(coffee);
        }
    }

}



@SuppressWarnings("hiding")
class CoffeeGenerator<Coffee> implements Generator<Coffee>, Iterable<Coffee> {
    private Class<?>[] types = { Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class, };
    private int times;

    public CoffeeGenerator(int times) {
        this.times = times;
    }

    public CoffeeGenerator() {
    }

    @SuppressWarnings("unchecked")
    public Coffee next() {
        Coffee coffee = null;
        try {
            coffee = (Coffee) types[new Random().nextInt(types.length)].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return coffee;
    }

    // Annoys inner class
    public Iterator<Coffee> iterator() {
        return new Iterator<Coffee>() {

            public boolean hasNext() {
                return times > 0;
            }

            public Coffee next() {
                times--;
                // call CoffeeGenerator's next method
                return CoffeeGenerator.this.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}

class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}

class Latte extends Coffee {
}

class Mocha extends Coffee {
}

class Cappuccino extends Coffee {
}

class Americano extends Coffee {
}

class Breve extends Coffee {
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
