package cn.qtec.learn.multiThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by duhc on 2018/4/11.
 */
public class SleepUtils {
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
