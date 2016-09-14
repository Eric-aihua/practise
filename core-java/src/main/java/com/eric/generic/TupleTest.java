package com.eric.generic;

/**
 * 
 * Provides the description
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
public class TupleTest {
    public static void main(String[] args) {
        System.out.println(getTuple2());
        System.out.println(getTuple3());
    }

    static Tuple2<String, Integer> getTuple2() {
        return new Tuple2<String, Integer>("Eric", 15);
    }

    static Tuple3<String, Integer, Integer> getTuple3() {
        return new Tuple3<String, Integer, Integer>("Eric", 15, 20);
    }
}

/*
 * Clients could still read the objects and do whatever they want with them, but they could not assign A or B
 * to anything else. The final declaration buys you the same safety, but the above form is shorter and simpler
 * 
 * You can see how the final  specification on the public fields prevents them from being 
  reassigned after construction, in the failure of the statement  ttsi.first = "there".  
 */
class Tuple2<A, B> {
    public final A a;
    public final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return "a:" + a + " b:" + b;
    }

}

class Tuple3<A, B, C> extends Tuple2<A, B> {

    public final C c;

    public Tuple3(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public String toString() {
        return super.toString() + " c:" + c;
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
