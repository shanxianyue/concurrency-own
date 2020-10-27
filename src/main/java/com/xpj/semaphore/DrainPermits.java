package com.xpj.semaphore;

import java.util.concurrent.Semaphore;

public class DrainPermits {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        System.out.println(semaphore.availablePermits());
        semaphore.acquire(1);
        semaphore.acquire(1);
        System.out.println(semaphore.drainPermits());
        System.out.println(semaphore.availablePermits());
        semaphore.release();
        System.out.println(semaphore.availablePermits());
        semaphore.release(10);
        System.out.println(semaphore.availablePermits());
        semaphore.acquire(1);
        System.out.println(semaphore.availablePermits());
        semaphore.release();
        System.out.println(semaphore.availablePermits());
        semaphore.acquire(11);
        System.out.println(semaphore.availablePermits());

    }
}
