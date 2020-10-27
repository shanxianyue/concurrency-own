package com.xpj.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BaseUse {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                service.testMethod();
            }
        });
        t.start();

        TimeUnit.SECONDS.sleep(5);
        service.downMethod();
    }

    static class Service{
        CountDownLatch latch = new CountDownLatch(1);
        public void testMethod(){

            try {
                System.out.println("before await");
                latch.await();
                System.out.println("after await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void downMethod(){
            System.out.println("countdown");
            latch.countDown();
        }
    }
}
