package cn.qtec.learn.multiThread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by duhc on 2018/4/10.
 */
public class MuliThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos)
            System.out.println(threadInfo.getThreadName());
            Thread.currentThread().isInterrupted();
            Thread.interrupted();
        }
    }

