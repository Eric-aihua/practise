package com.eric.reflect;

public class GenericClassReference {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    public static void main(String[] args) {
        Class intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class; // Same thing
        intClass = double.class;
        // genericIntClass = double.class; // Illegal

        // Class<Number> genericNumberClass = Integer.class;//Integer Class object is not a subclass of the Number Class

        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
        
        //
        try {
            Sun sun=Sun.class.newInstance();
            Class<Parent> parent=(Class<Parent>) sun.getClass().getSuperclass();
            System.out.println(parent.newInstance());
            Class<? super Sun> parent2= Sun.class.getSuperclass();
            System.out.println(parent2.newInstance());
            Object obj=parent2.newInstance();
            System.out.println(obj);
            
            Parent parent3=Sun.class.cast(new Parent());
            
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
    }

   
}
class Parent {
}

class Sun extends Parent{
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
