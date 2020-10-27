package com.xpj.thread;

import org.junit.Test;

public class WaitTest {

    @Test
    public void test002() {
        Object monitor = new Object();
        monitor.notify();
    }

    @Test
    public void waitTest() throws InterruptedException {
        Object monitor = new Object();
        new Thread(() -> {
            synchronized (monitor) {
                try {
                    System.out.println("will wait");
                    monitor.wait();
                    System.out.println("notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100);

        new Thread(() -> {
            synchronized (monitor) {
                monitor.notify();
                System.out.println("notify");
            }
        }).start();
    }
}
