package cn.qtec.learn.multiThread;

/**
 * Created by duhc on 2018/4/11.
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class){
            m();
        }

    }
    public static synchronized void m(){
    }
}
