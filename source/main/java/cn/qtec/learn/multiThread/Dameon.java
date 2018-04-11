package cn.qtec.learn.multiThread;

/**
 * Created by duhc on 2018/4/11.
 */
public class Dameon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DameonRunner(),"DameonRunner");
        thread.setDaemon(true);
        thread.start();
        SleepUtils.second(10);
    }
    static class DameonRunner implements Runnable{
        @Override
        public void run() {
           try {
               System.out.println("执行这句话2");
               SleepUtils.second(2);
           }finally {
               System.out.println("执行了这句话。。。");
           }
        }
    }
}
