package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            es.execute(new LiftOff());
        }
        es.shutdown();
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
