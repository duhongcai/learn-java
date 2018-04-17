package cn.qtec.learn.lock;

import cn.qtec.learn.multiThread.SleepUtils;

import java.util.concurrent.locks.Lock;

/**
 * Created by duhc on 2018/4/17.
 */
public class TwinsLockTest {
    public static void main(String[] args) {
        Lock lock = new TwinsLock();
        class Writer extends Thread{
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            Writer writer = new Writer();
            writer.setDaemon(true);
            writer.start();
        }
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println("");
        }
    }
}
