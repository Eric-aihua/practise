package com.eric.reflect;

import java.util.Random;
/**
 * static&&constant not trigger initialization
 * Class literals not trigger initialization
 * */

public class Initialize {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        simpleInit();
        simpleInit2();

    }

    private static void simpleInit2() {
        System.out.println(Initable.staticFinal);// does not trigger initialization
        System.out.println(Initable.staticFinal2);// will trigger initialization
        System.out.println(Initable2.staticNonFinal);// will trigger initialization
        System.out.println(Initable3.staticNonFinal);// will trigger initialization
    }

    private static void simpleInit() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("before create Candy");
        Class.forName("com.eric.reflect.Candy");// will trigger initialization
        System.out.println("after create Candy");
        System.out.println("before create Gum");
        Class<?> class2 = Gum.class;// does not trigger initialization
        System.out.println("after create Gum");
        class2.newInstance();// will trigger initialization
        System.out.println("before create Cookie");
        new Cookie();// will trigger initialization
        System.out.println("after create Cookie"+"\n");
    }
}

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = new Random().nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}

class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
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
