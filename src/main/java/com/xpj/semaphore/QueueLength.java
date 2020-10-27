package com.xpj.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class QueueLength {

    public static void main(String[] args) {
        Service2 service2 = new Service2();
        Thread threadA = new Thread(() -> service2.testMethod(), "A");
        Thread threadB = new Thread(() -> service2.testMethod(), "B");
        Thread threadC = new Thread(() -> service2.testMethod(), "C");

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static class Service2 {
        Semaphore semaphore = new Semaphore(1);

        public void testMethod(){
            try {
                System.out.println(Thread.currentThread().getName() + " 1: " + semaphore.getQueueLength());
                semaphore.acquire(1);
                System.out.println(Thread.currentThread().getName() + " 2: " + semaphore.getQueueLength());
                TimeUnit.SECONDS.sleep(5);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
