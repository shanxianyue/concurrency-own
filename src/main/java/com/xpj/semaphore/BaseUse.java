package com.xpj.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class BaseUse {



    public static void main(String[] args) {
        Service1 service1 = new Service1();
        Thread threadA = new Thread(() -> service1.testMethod(), "A");
        Thread threadB = new Thread(() -> service1.testMethod(), "B");
        Thread threadC = new Thread(() -> service1.testMethod(), "C");

        threadA.start();
        threadB.start();
        threadC.start();
    }

    static class Service1 {
        private Semaphore semaphore = new Semaphore(2);

        public void testMethod(){
            try {
                semaphore.acquire(2);
                System.out.println(Thread.currentThread().getName() + " begin time: " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " end time: " + System.currentTimeMillis());
                semaphore.release();
                System.out.println(semaphore.availablePermits());
                semaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
