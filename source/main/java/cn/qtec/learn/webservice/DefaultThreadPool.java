package cn.qtec.learn.webservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by duhc on 2018/4/11.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUMBER = 10;
    private static final int DEFAULT_WORKER_NUMER = 5;
    private static final int MIN_WORKER_NUMER = 1;
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private int workerNum = DEFAULT_WORKER_NUMER;
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMER);
    }

    public DefaultThreadPool(int num) {
        workerNum = num>MAX_WORKER_NUMBER?MAX_WORKER_NUMBER:num<MIN_WORKER_NUMER?MIN_WORKER_NUMER:num;
        initializeWorkers(workerNum);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
            if (job!=null){
                synchronized (jobs){
                    jobs.addLast(job);
                    jobs.notify();
                }
            }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shundown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            if (num + workerNum > MAX_WORKER_NUMBER){
                num = MAX_WORKER_NUMBER - workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs){
            if (num>=workerNum) throw new IllegalArgumentException("beyond workNum");
            int count = 0;
            while (count < num){
                Worker worker = workers.get(count);
                if (workers.remove(worker)){
                    count++;
                }
            }
            this.workerNum -= num;
        }
    }

    @Override
    public int getPoolSize() {
        return workers.size();
    }

    class Worker implements Runnable{
        private  volatile boolean running = true;
        @Override
        public void run() {
            while (running){
                    Job job = null;
                    synchronized (jobs){
                        while (jobs.isEmpty()){
                            try {
                                jobs.wait();
                            }catch (Exception e){
                                e.printStackTrace();
                                //感知外部对WorkerThread的中断
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        job = jobs.removeFirst();
                    }
                    if (job!= null){
                        job.run();
                    }
            }
        }
        public void shundown(){
            running  = false;
        }
    }
}
