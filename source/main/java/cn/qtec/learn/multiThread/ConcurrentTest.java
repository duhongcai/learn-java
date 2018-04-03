package cn.qtec.learn.multiThread;

/**
 * Created by duhc on 2018/4/3.
 */
public class ConcurrentTest {
    public static final long count = 1000000L;
    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0 ;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0 ;
        //增加运行时间 便于计时
        for (long i = 0; i < count; i++) {
            b--;
        }
        long end = System.currentTimeMillis();
        //thread.join() thread 执行完以后再往下执行
        thread.join();
        System.out.println("concurrency cost time:" + (end - start)+", b = "+b);
    }
    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long end = System.currentTimeMillis();
        System.out.println("serial cost time:" + (end - start)+" , b = "+b);
    }
}
