package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool(new DaemonThreadFactory());
        es.execute(new SimpleDaemons());
        Thread.sleep(1000);
        System.out.println("Main finished!");
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
