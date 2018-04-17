package cn.qtec.learn.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by duhc on 2018/4/17.
 */
public class TwinsLock implements Lock {
    private Sync sync = new Sync(3);
    private static class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if (count <= 0){
                throw new IllegalArgumentException("参数异常");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (;;){
                int current = getState();
                int newCount = current - arg;
                if (newCount<0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int current = getState();
                int newCurrent = current + arg;
                if (compareAndSetState(current,newCurrent)){
                    return true;
                }
            }
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) == 1?true:false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
