package com.eric.concurrency;

public class BasicThreads {

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
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
