package cn.qtec.learn.jucDemo.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by duhc on 2018/4/12.
 */
public class BankWaterService implements Runnable {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);
    private Executor executor = Executors.newFixedThreadPool(4);
    private ConcurrentHashMap<String, Integer> sheetBankWaterCnt = new ConcurrentHashMap<String, Integer>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetBankWaterCnt.put(Thread.currentThread().getName(), finalI);
                    try {
                        System.out.println("执行了" + finalI);
                        /**
                         * 调用await（）后有两个前置条件才能继续执行
                         *          1：cyclicBarrier.await()被调用4次
                         *          2：this对象执行完毕 即run方法执行完毕 (this是在cyclicBarrier执行四次await()方法以后才会执行)
                         */
                        cyclicBarrier.await();
                        cyclicBarrier.reset();
                        cyclicBarrier.await();
                        System.out.println("执行完" + finalI);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        System.out.println("先执行");
        int result = 0;
        for (Map.Entry<String, Integer> entry : sheetBankWaterCnt.entrySet()) {
            result += entry.getValue();
        }
        sheetBankWaterCnt.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
