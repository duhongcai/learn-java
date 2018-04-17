package cn.qtec.learn.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by duhc on 2018/4/17.
 */
public class FairAndUnfairTest {
    private static ReentrantLock2 fireLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfireLock = new ReentrantLock2(false);
    private static void testLock(){
        for (int i = 0; i < 5; i++) {
            Job job1 = new Job(fireLock);
            Job job2 = new Job(unfireLock);
            Thread thread = new Thread(job1);
            thread.start();
            Thread t =new Thread(job2);
            t.start();
        }
    }
    private static class ReentrantLock2 extends ReentrantLock{
        private boolean faire;
        public ReentrantLock2(boolean faire) {
            super(faire);
        }
        public Collection<Thread> getQueueThreads(){
            List<Thread> arrayList = new ArrayList<>(getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
    private static class Job extends Thread{
        private ReentrantLock2 lock;
        public Job(ReentrantLock2 lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            System.out.println(lock.getQueueThreads());
        }
    }
    public static void main(String[] args) {
            testLock();
    }
}
