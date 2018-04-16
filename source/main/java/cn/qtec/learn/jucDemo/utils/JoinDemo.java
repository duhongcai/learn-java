package cn.qtec.learn.jucDemo.utils;

/**
 * Created by duhc on 2018/4/12.
 */
public class JoinDemo {
    /**
     * join()
     * while(isAlive()){
     *     wait(0);
     * }
     * wait(0)表示永远等待下去，知道join()线程中断以后，this.notifyAll()唤醒wait的方法,这一步又由JVM实现，JDK中么有
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finished");
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finished");
            }
        });
        //线程没有启动 join无效
        parser1.join();
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("main finished");
    }
}
