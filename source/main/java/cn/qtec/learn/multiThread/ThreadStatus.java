package cn.qtec.learn.multiThread;

/**
 * Created by duhc on 2018/4/11.
 */
public class ThreadStatus {
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(1000);
            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(1000);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        new Thread(new Blocked(),"BlockThread-1").start();
        new Thread(new Blocked(),"BlockThread-2").start();
    }
    /**
     * "BlockThread-2" #14 prio=5 os_prio=0 tid=0x0000000058d09000 nid=0x1378 waiting on condition [0x000000005aa3f000]
     java.lang.Thread.State: TIMED_WAITING (sleeping)
     at java.lang.Thread.sleep(Native Method)
     at java.lang.Thread.sleep(Thread.java:340)
     at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     at cn.qtec.learn.multiThread.SleepUtils.second(SleepUtils.java:11)
     at cn.qtec.learn.multiThread.ThreadStatus$Blocked.run(ThreadStatus.java:36)
     - locked <0x00000000d5dbf8b0> (a java.lang.Class for cn.qtec.learn.multiThread.ThreadStatus$Blocked)
     at java.lang.Thread.run(Thread.java:748)

     "BlockThread-1" #13 prio=5 os_prio=0 tid=0x0000000058d08000 nid=0x1960 waiting for monitor entry [0x000000005a8ff000]
     java.lang.Thread.State: BLOCKED (on object monitor)
     at cn.qtec.learn.multiThread.ThreadStatus$Blocked.run(ThreadStatus.java:36)
     - waiting to lock <0x00000000d5dbf8b0> (a java.lang.Class for cn.qtec.learn.multiThread.ThreadStatus$Blocked)
     at java.lang.Thread.run(Thread.java:748)

     "WaitingThread" #12 prio=5 os_prio=0 tid=0x0000000058d03000 nid=0x2d8 in Object.wait() [0x000000005a7df000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x00000000d5dbb348> (a java.lang.Class for cn.qtec.learn.multiThread.ThreadStatus$Waiting)
     at java.lang.Object.wait(Object.java:502)
     at cn.qtec.learn.multiThread.ThreadStatus$Waiting.run(ThreadStatus.java:22)
     - locked <0x00000000d5dbb348> (a java.lang.Class for cn.qtec.learn.multiThread.ThreadStatus$Waiting)
     at java.lang.Thread.run(Thread.java:748)

     "TimeWaitingThread" #11 prio=5 os_prio=0 tid=0x0000000058d00000 nid=0x210c waiting on condition [0x000000005a51e000]
     java.lang.Thread.State: TIMED_WAITING (sleeping)
     at java.lang.Thread.sleep(Native Method)
     at java.lang.Thread.sleep(Thread.java:340)
     at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     at cn.qtec.learn.multiThread.SleepUtils.second(SleepUtils.java:11)
     at cn.qtec.learn.multiThread.ThreadStatus$TimeWaiting.run(ThreadStatus.java:11)
     at java.lang.Thread.run(Thread.java:748)

     */
}
