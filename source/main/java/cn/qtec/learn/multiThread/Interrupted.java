package cn.qtec.learn.multiThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by duhc on 2018/4/11.
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepRunner = new Thread(new SleepRunner(),"SleepRunner");
        Thread busyRunner = new Thread(new BusyRunner(),"BusyRunner");
        sleepRunner.setDaemon(true);
        busyRunner.setDaemon(true);
        sleepRunner.start();
        busyRunner.start();
        TimeUnit.SECONDS.sleep(5);
        sleepRunner.interrupt();
        busyRunner.interrupt();
        System.out.println("SleepRunner interrupt is:" + sleepRunner.isInterrupted());
        System.out.println("BusyRunner interrupt is:" + busyRunner.isInterrupted());
        SleepUtils.second(2);
    }
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(10);
            }
        }
    }
    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }
}
