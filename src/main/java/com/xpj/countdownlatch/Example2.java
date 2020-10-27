package com.xpj.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Example2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " ready");
                        latch.await();
                        System.out.println(Thread.currentThread().getName() + " go");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread" + i).start();

        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("start");
        latch.countDown();
    }
}
