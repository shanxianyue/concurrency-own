package com.xpj.producer_consumer;

import java.util.LinkedList;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {

        MyBlockQueue<String> queue = new MyBlockQueue(16);
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
        Thread.sleep(1000);
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

    static class MyBlockQueue<T> {
        private LinkedList<T> queue = null;
        private int max = 0;


        public MyBlockQueue(int max) {
            this.max = max;
            queue = new LinkedList<>();
        }

        public synchronized void put(T e) throws InterruptedException {

            while (queue.size() >= max) {
                this.wait();
            }

            queue.offer(e);
            this.notifyAll();
        }

        public synchronized T take() throws InterruptedException {

            while (queue.size() <= 0) {
                this.wait();
            }
            T result = queue.poll();
            this.notifyAll();
            return result;
        }
    }
}
