package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {

    /**
     * @param args
     */
    @Override
    public void run() {
        while (cutDown-- > 0) {
            System.out.println(status());
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            //sleep的时间对结果会有比较大的影响
            es.execute(new SleepingTask());
            //es.execute(new LiftOff());
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
