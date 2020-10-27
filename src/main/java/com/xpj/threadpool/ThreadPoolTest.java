package com.xpj.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20));
        for (int i = 0; i < 30; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(pool.getPoolSize());
            System.out.println(pool.getQueue().size());
        }

    }
}
