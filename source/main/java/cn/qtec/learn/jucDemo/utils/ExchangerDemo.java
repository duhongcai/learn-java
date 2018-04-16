package cn.qtec.learn.jucDemo.utils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by duhc on 2018/4/12.
 */
public class ExchangerDemo {
    private static final Exchanger<String> exch = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "银行流水A";
                try {
                    exch.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水B";
                try {
                    String A = exch.exchange(B);
                    if (A.equals(B)) {
                        System.out.println("same");
                    } else {
                        System.out.println("no same");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
