package cn.qtec.learn.multiThread;

/**
 * Created by duhc on 2018/4/4.
 */
public class FinalTest {
    int i;
    int j;
    static  FinalTest finalTest;
    public FinalTest(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static void writer(){
        finalTest = new FinalTest(10,10);
    }
    public static void reader(String name){
        FinalTest o = finalTest;
        System.out.println(name+"--i的值为:"+o.i);
        System.out.println(name+"--j的值为："+o.j);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
             final int finalI = i;
            Thread thread = new Thread(new Runnable(){
               @Override
               public void run() {
                   writer();
                   reader("thread"+ finalI);
               }
           },"Thread-"+i);
           thread.start();
        }
    }
}
