package com.xpj.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 公平锁与非公平锁测试
 * 公平锁是指获得许可的顺序与线程执行到acquire的顺序有关
 *
 */
@SuppressWarnings("ALL")
public class FairUnFailTest {

    public static void main(String[] args) {
        Service3 service3 = new Service3();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> service3.testMethod(), "Thread" + i);
            threads[i].start();
        }
    }

    static class Service3{

        Semaphore semaphore = new Semaphore(1, true);
//        Semaphore semaphore = new Semaphore(2, false);

        public void testMethod(){
            try {
                System.out.println( "1: " + Thread.currentThread().getName());
                semaphore.acquire(1);
                System.out.println(Thread.currentThread().getName());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
