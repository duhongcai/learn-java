package cn.qtec.learn.jucDemo.utils;

import java.util.concurrent.CountDownLatch;

/**
 * Created by duhc on 2018/4/12.
 */
public class CountDownLatchDemo {
    static CountDownLatch downLatch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                downLatch.countDown();
                System.out.println(2);
                downLatch.countDown();
            }
        }).start();
        /**
         * 阻塞该线程，知道countDown变成0
         */
        downLatch.await();
        System.out.println(3);
    }
}
