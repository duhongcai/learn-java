package cn.qtec.learn.jucDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by duhc on 2018/4/12.
 */
public class ForkJoinTest extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end-start) <= THRESHOLD;
        if (canCompute){
            for (int i=start;i<=end;i++){
                sum += i;
            }
        }else {
            int middle = (start + end)/2;
            ForkJoinTest forkjoin = new ForkJoinTest(start,middle);
            ForkJoinTest forkjoin2 = new ForkJoinTest(middle+1,end);
            forkjoin.fork();
            forkjoin2.fork();
            int leftResult = forkjoin.join();
            int rightResult = forkjoin2.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest forkJoinTest = new ForkJoinTest(1,4);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(forkJoinTest);
        System.out.println(submit.get());
    }
}
