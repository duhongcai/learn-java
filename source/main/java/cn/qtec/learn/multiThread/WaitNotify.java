package cn.qtec.learn.multiThread;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by duhc on 2018/4/11.
 */
public class WaitNotify {
    static volatile boolean flag = true;
    static Object lock = new Object();

    /**
     * Thread[WaitThread,5,main] flag is true,wait @  14:18:02
     Thread[NotifyThread,5,main] hold lock.notify @  14:18:03
     Thread[NotifyThread,5,main] hold lock again.sleep @ 14:18:05
     醒了
     Thread[WaitThread,5,main] flag is false.running @ 14:18:07
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
            Thread wait = new Thread(new Wait(),"WaitThread");
            wait.start();
            TimeUnit.SECONDS.sleep(1);
            Thread notify = new Thread(new Notify(),"NotifyThread");
            notify.start();
    }
    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread() + " flag is true,wait @  " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                        System.out.println("醒了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false.running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock.notify @  " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(2);
            }
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again.sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(2);
            }
        }
    }
}
