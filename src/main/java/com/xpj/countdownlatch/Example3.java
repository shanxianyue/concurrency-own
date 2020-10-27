package com.xpj.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Example3 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch comingTag = new CountDownLatch(10); //裁判等待所有运动员到来
        CountDownLatch waitTag = new CountDownLatch(1); //运动员等待裁判说准备
        CountDownLatch waitRunTag = new CountDownLatch(10); //等待起跑
        CountDownLatch beginTag = new CountDownLatch(1); //起跑
        CountDownLatch endTag = new CountDownLatch(10); //等待运动员到达终点

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "到往起跑点赶！");
                    long useTime = (long) Math.random() * 10;
                    TimeUnit.SECONDS.sleep(useTime);
                    System.out.println(Thread.currentThread().getName() + "使用了" + useTime +"小时到达起跑点了！");
                    comingTag.countDown();
                    System.out.println(Thread.currentThread().getName() + "等待裁判说准备！");
                    waitTag.await();
                    System.out.println(Thread.currentThread().getName() + "开始准备起跑！");
                    TimeUnit.SECONDS.sleep((long) Math.random() * 10);
                    waitRunTag.countDown();
                    beginTag.await();
                    System.out.println(Thread.currentThread().getName() + "起跑，且用时不明确！");
                    TimeUnit.SECONDS.sleep((long) Math.random() * 10);
                    System.out.println(Thread.currentThread().getName() + "到达终点！");
                    endTag.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "运动员" + i).start();
        }

        System.out.println("裁判等待运动员的到来！");
        comingTag.await();
        System.out.println("所有运动员已到达！各就各位前裁判巡视5秒钟！");
        TimeUnit.SECONDS.sleep(5);
        waitTag.countDown();
        System.out.println("各就各位！");
        TimeUnit.SECONDS.sleep(2);
        waitRunTag.await();
        System.out.println("发令枪响起！");
        beginTag.countDown();
        endTag.await();
        System.out.println("所有运动员都已到达终点，开始统计名次");

    }
}
