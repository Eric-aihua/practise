package com.eric.concurrency;

public class LiftOff implements Runnable {
    protected int cutDown = 10;
    public static int count = 0;
    private int id = count++;

    public LiftOff() {
    }

    public LiftOff(int cutDown) {
        this.cutDown = cutDown;
    }

    public String status() {
        return "ID:" + id + " cutDown:" + (cutDown > 0 ? cutDown : "cut over!");
    }

    // Thread.yield(); 放在while循环的里面和外面，对于调用者来说结果会有很大的不同
    @Override
    public void run() {
        while (cutDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        new LiftOff().run();
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
