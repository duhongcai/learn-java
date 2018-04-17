package cn.qtec.learn.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by duhc on 2018/4/16.
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i =0 ;
    private int j =0 ;

    private void count(){
        i++;
    }

    private void safeCnt(){
         j = atomicI.incrementAndGet();
//        for (;;){
//            int i = atomicI.get();
//            if (atomicI.compareAndSet(i,++i)){
//                break;
//            }
//        }
    }
    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 10000; i1++) {
                            cas.count();
                            cas.safeCnt();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }
}
