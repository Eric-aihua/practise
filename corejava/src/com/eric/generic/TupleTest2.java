package com.eric.generic;

/**
 * 
 * Type argument inference, together with static imports, allows the tuples we saw earlier to be rewritten into a more
 * general-purpose librar y. Here, tuples can be created using an overloaded static method, comrare with TupleTest's
 * getTuple2() , this method has more flexible
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class TupleTest2 {
    public static void main(String[] args) {
        System.out.println(getTuple2("Eric", 10));
        System.out.println(getTuple2("Eric", "Simon"));
        System.out.println(getTuple2(10, 10));
    }

    static <T, K> Tuple2<T, K> getTuple2(T t, K k) {
        return new Tuple2<T, K>(t, k);
    }

    static <T, K, V> Tuple3<T, K, V> getTuple3(T t, K k, V v) {
        return new Tuple3<T, K, V>(t, k, v);
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
