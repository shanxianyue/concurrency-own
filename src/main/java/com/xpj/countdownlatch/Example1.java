package com.xpj.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Example1 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        Thread ts[] = new Thread[10];
        for (int i = 0; i < ts.length; i++) {

            ts[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始准备");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 准备完毕");
                latch.countDown();
            }, "运动员" + i);
            ts[i].start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有运动员已各就各位");
    }
}
