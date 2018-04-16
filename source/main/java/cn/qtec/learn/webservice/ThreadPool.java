package cn.qtec.learn.webservice;

/**
 * Created by duhc on 2018/4/11.
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);
    void shutdown();
    void addWorkers(int num);
    void removeWorker(int num);
    int getPoolSize();
}
