package com.eric.concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试使用使用线程返回值的使用方法
 * @author Eric
 *
 */
public class CallableDemo {

    static class Sums implements Callable<Integer> {
        private int a;
        private int b;

        public Sums(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer call() throws Exception {
            return a + b;
        }

    }

    static class Computer {
        int a, b, sums;

        public Computer(int a, int b, int sums) {
            this.a = a;
            this.b = b;
            this.sums = sums;
        }

        public String toString() {
            return a + "+" + b + "=" + sums;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();
        ArrayList<Computer> lists = new ArrayList<Computer>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                lists.add(new CallableDemo.Computer(i, j, es.submit(new CallableDemo.Sums(i, j)).get()));
            }
        }
        es.shutdown();

        for (Computer computer : lists) {
            System.out.println(computer);
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
