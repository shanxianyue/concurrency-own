package com.xpj.sleep;

public class StopDuringSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (!Thread.currentThread().isInterrupted() && num <= 3) {
                System.out.println(num);
                num++;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println();
        };
        Thread thread = new Thread(runnable);

        thread.start();
        System.out.println(thread.getState());


        Thread.sleep(5);
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(thread.getState());
        System.out.println(thread.isInterrupted());
    }
}
