package com.eric.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

interface HasBatteries {
    void print1();
}

interface Waterproof {
    void print2();
}

interface Shoots {
    void print3();
}

class Toy {

    Toy() {
    }

    Toy(int i) {
    }
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }

    public void print3() {
        // TODO Auto-generated method stub

    }

    public void print2() {
        // TODO Auto-generated method stub

    }

    public void print1() {
        // TODO Auto-generated method stub

    }
}

public class ClassInformation {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    static void printClassInfo(Class<?> c) {
        System.out.println("Name:" + c.getName());
        System.out.println("SuperClass:" + c.getSuperclass());
        System.out.println("Modify:" + c.getModifiers());
        System.out.println("CanonicalName" + c.getCanonicalName());

        System.out.println("Interfaces:");
        for (Class in : c.getInterfaces()) {
            printClassInfo(in);
        }
        System.out.println("isInterface:" + c.isInterface());
        System.out.print("Fields:");
        for (Field field : c.getFields()) {
            System.out.println(field.getName() + " ");
        }
        System.out.print("\n Methods:");
        for (Method field : c.getMethods()) {
            System.out.println(field.getName() + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        try {
            printClassInfo(Class.forName("com.eric.reflect.FancyToy"));
            for (Class in : FancyToy.class.getInterfaces()) {
                printClassInfo(in);
            }
            printClassInfo(FancyToy.class.getSuperclass());
            printClassInfo(new FancyToy().getClass());
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
