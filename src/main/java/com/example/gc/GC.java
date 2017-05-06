package com.example.gc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 17-5-6.
 */
public class GC {
    public static void test2(int ts) throws InterruptedException {
        final Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(ts);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 1000; k++) {
                        set.add(Thread.currentThread().getName()
                                + "[" + k + "]" + "{" + UUID.randomUUID().toString() + "}");
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(System.currentTimeMillis()-start);
    }


    public static void test(int ts) throws InterruptedException {
        final Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(ts);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 1000; k++) {
                        set.add(Thread.currentThread().getName()
                                + "[" + k + "]" + "{" + UUID.randomUUID().toString() + "}");
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(System.currentTimeMillis()-start);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test(64);
            test2(64);
            System.out.println("-----");
        }
    }
}
