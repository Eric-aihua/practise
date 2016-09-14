/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.eric.polymorphism;



/**
 *
 * @author Eric
 */
public class InitizalizeOrder2 {

    public static void main(String args[]) {
      // System.out.println( OrderTest1.temp);
       OrderTest1 ot=new OrderTest1();
       ot.print("sun");
        
    }
}

class OrderTest1 {

    public static Temp temp = new Temp("static property init.....");
    private Temp temp2 = new Temp("not static property init....");

    static {
        System.out.println("static code area init.....");

    }

    {
        System.out.println("not static code area init..... ");
    }

    public OrderTest1() {
        System.out.println("Constructor method....");
    }
    public static void print(){
        System.out.println("static method output.....");
    }
    public void print(String name){
        System.out.println("not static methout output....");
    }
    
}
