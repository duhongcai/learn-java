package cn.qtec.learn.multiThread;

/**
 * Created by duhc on 2018/4/3.
 */
public class ThreadjoinTest implements Runnable {

    @Override
    public void run() {
        System.out.println("开始运行线程");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadjoinTest());
        thread.start();
        thread.join();
        /**
         * 如果有join，for里面的逻辑会等到线程结束后再运行
         *  join底层实际上是由wait()实现，只要当该线程isActive()时，就会wait()
         * 然后wait()后怎么退出？
         *  sleep()与wait()的区别
         *      1：sleep()是Thread的静态方法，休眠一段时间后自动唤醒，
         *           wait()是普通方法，wait以后需要持有对象锁的线程唤醒
         *      2：sleep()不释放锁
         *              wait()释放锁
         */
        final int flag = 1000;
        for (int i = 0; i < flag; i++) {
            System.out.println("处理一些任务");
        }
    }
}
