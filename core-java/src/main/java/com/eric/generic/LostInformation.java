package com.eric.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * Class.getTypeParameters( ) "returns an array of TypeVariable objects that represent the type variables declared by
 * the generic declaration
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<Frob>();
        Map<Frob, Fnorkle> map = new HashMap<Frob, Fnorkle>();
        Quark<Fnorkle> quark = new Quark<Fnorkle>();
        Particle<Long, Double> p = new Particle<Long, Double>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(new LinkedList().getClass().getTypeParameters()));
    }
}

class Frob {
}

class Fnorkle {
}

class Quark<T> {
}

class Particle<POSITION, MOMENTUM> {
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
