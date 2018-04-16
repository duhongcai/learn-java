package cn.qtec.learn.jucDemo.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by duhc on 2018/4/12.
 */
public class CycleBarrierTest {
    //  A线程优先执行
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new A());
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    System.out.println("执行了1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        cyclicBarrier.await();
//        /**
//         * 执行不到这里
//         */
//        System.out.println("执行了");
//        cyclicBarrier.await();
        System.out.println("执行了2");
    }
    static class A implements Runnable{
        @Override
        public void run() {
            System.out.println("开始执行了");
        }
    }
}
