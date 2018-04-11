package cn.qtec.learn.multiThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by duhc on 2018/4/11.
 */
public class Join {
    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous),String.valueOf(i));
            thread.start();
            previous = thread;
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - terminate");
    }
    static class Domino implements Runnable{
        private Thread thread;
        public Domino(Thread thread) {
            this.thread = thread;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 启动了 ");
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminated");
        }
    }
}
