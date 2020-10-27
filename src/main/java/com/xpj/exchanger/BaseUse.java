package com.xpj.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class BaseUse {
    public static void main(String[] args) throws InterruptedException {
        testMethod2();
    }

    public static void testMethod(){
        Exchanger<String> exchanger = new Exchanger<>();
        Thread t = new Thread(() -> {
            try {
                System.out.println("thread running");
                String data = exchanger.exchange("thread data");
                System.out.println("thread received: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        try {
            String data = null;
            data = exchanger.exchange("main data");
            System.out.println("main received: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main end");
    }

    public static void testMethod2(){
        Exchanger<String> exchanger = new Exchanger<>();
        Thread[] ts = new Thread[3];
        for (int i = 0; i < ts.length; i++) {
            int j = i;
            ts[i] = new Thread(() -> {
                try {
                    String data = exchanger.exchange("t" + j + " data");
                    System.out.println("t" + j + " received: " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            ts[i].start();
        }

    }
}
