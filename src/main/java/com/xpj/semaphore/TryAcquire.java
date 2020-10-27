package com.xpj.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class TryAcquire {

    public static void main(String[] args) {

        Service4 service4 = new Service4();

        Thread threads[] = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> service4.testMethod2(), "Thread" + i);
            threads[i].start();
        }


    }

    static class Service4{
        private Semaphore semaphore = new Semaphore(1);

        public void testMethod(){
            if (semaphore.tryAcquire()){
                System.out.println(Thread.currentThread().getName() + " in");
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String s = new String();
                }
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " end");
            }else{
                System.out.println(Thread.currentThread().getName() + " not in");
            }
        }


        public void testMethod2(){
            try {
                if (semaphore.tryAcquire(1, TimeUnit.SECONDS)){
                    System.out.println(Thread.currentThread().getName() + " in");
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        String s = new String();
                    }
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " end");
                }else{
                    System.out.println(Thread.currentThread().getName() + " not in");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
