package cn.qtec.learn.multiThread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by duhc on 2018/4/11.
 */
public class ThreadLocalTest {
    private static final  ThreadLocal<Long> TIME_THREADLOCAL=new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };
    private static final  ThreadLocal<String> TIME_THREADLOCAL2=new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "heiheihei";
        }
    };
    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        TIME_THREADLOCAL2.set("hahahah");
        TIME_THREADLOCAL2.set("hahassshah");

    }

    public static final long end(){
        return System.currentTimeMillis()-TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost time: " + end());
        System.out.println(TIME_THREADLOCAL2.get());
    }
}
