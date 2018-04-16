package cn.qtec.learn.jucDemo;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by duhc on 2018/4/12.
 */
public class HashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String,String> map = new HashMap<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    },"ftf-"+i).start();
                }    
            }
        },"ftf");
        thread.start();
        thread.join();
    }
}
