package com.xpj.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example3 {

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

    static class MyBlockQueue<T> {
        private LinkedList<T> queue = null;
        private int max = 0;
        private Lock lock = new ReentrantLock();
        private Condition notFull = lock.newCondition();
        private Condition notEmpty = lock.newCondition();

        public MyBlockQueue(int max) {
            this.max = max;
            queue = new LinkedList<>();
        }

        public void put(T e) throws InterruptedException {
            lock.lock();
            try {

                while (queue.size() >= max) {
                    notFull.await();
                }

                queue.offer(e);
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public T take() throws InterruptedException {
            lock.lock();
            try {

                while (queue.size() <= 0) {
                    notEmpty.await();
                }

                T result = queue.poll();
                notFull.signalAll();
                return result;
            } finally {
                lock.unlock();
            }
        }
    }
}
