package com.xpj.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Example2 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        new Thread(() -> {
            try {
                while (true) {
                    String e = (int) (Math.random() * 100) + "";
                    System.out.println("producer: " + e);
                    queue.put(e);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(10000);
        new Thread(() -> {
            try {
                while (true) {
                    String take = queue.take();
                    System.out.println("consumer: " + take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
