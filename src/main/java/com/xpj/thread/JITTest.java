package com.xpj.thread;

public class JITTest {

    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while(flag){
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                int a = flag ? 2 + 3 : 3 + 4;
            }
        }).start();
        Thread.sleep(2000);
        new Thread(() -> {
            flag = false;
            System.out.println("set flag to false");
        }).start();
    }
}
