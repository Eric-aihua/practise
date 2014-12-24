package com.eric.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The makeList( ) method shown here produces the same functionality as the standard library’s java.util.Arrays.asList(
 * ) method.
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */

public class GenericVarargs {
    static <T> List<T> asList(T... args) {
        ArrayList<T> al = new ArrayList<T>();
        for (int i = 0; i < args.length; i++) {
            al.add(args[i]);
        }
        return al;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(asList("sdfasdfwtgcetdasdfasd".split("")));
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
