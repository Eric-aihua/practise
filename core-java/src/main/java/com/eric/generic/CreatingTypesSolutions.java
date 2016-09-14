package com.eric.generic;

/**
 * 
 * although in c++ could use new T() to creating a instance of type, but java not support this approach, this class
 * provider 3 solutions for this situation
 * @version $Revision: $ $Name: $
 */
public class CreatingTypesSolutions {
    public static void main(String[] args) {
        // solution 1: pass in a factory object, and use that to make the new instance
        ClassAsFactory<Employ> caf1 = new ClassAsFactory<Employ>(Employ.class);
        /*
         * This compiles, but fails with ClassAsFactory<Integer> because Integer has no default constructor. Because the
         * error is not caught at compile time, this approach is frowned upon by the Sun folks. They suggest instead
         * that you use solution2
         */
        // ClassAsFactory<Integer> caf2 = new ClassAsFactory<Integer>(Integer.class);
        // solution 2:
        ClassAsFactory2<Employ> caf21 = new ClassAsFactory2<Employ>(new EmployFactory());
        ClassAsFactory2<Integer> caf22 = new ClassAsFactory2<Integer>(new IntegerFactory());
        // solution 3: Template Method design pattern
        IntegerGeneric ig1=new IntegerGeneric();
        System.out.println(ig1.create());
    }

}

class Employ {
}

class ClassAsFactory<T> {
    public ClassAsFactory(Class<T> t) {
        try {
            T tObj = t.newInstance();
            System.out.println(tObj.getClass().getSimpleName() + " object!");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

// solution 2 :intgerface
interface FactoryI<T> {
    T create();
}

class ClassAsFactory2<T> {
    public <F extends FactoryI<T>> ClassAsFactory2(F factory) {
        System.out.println(factory.create().getClass().getSimpleName() + " create successful");
    }
}

class EmployFactory implements FactoryI<Employ> {

    public Employ create() {
        return new Employ();
    }

}

class IntegerFactory implements FactoryI<Integer> {

    public Integer create() {
        return new Integer(0);
    }

}

// solution 3: Template Method design pattern
abstract class GenericWithCreate<T> {
    final T element;

    GenericWithCreate() {
        element = create();
    }

    abstract T create();
}

class IntegerGeneric extends GenericWithCreate<Integer> {
   
    @Override
    Integer create() {
        return new Integer("4");
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
