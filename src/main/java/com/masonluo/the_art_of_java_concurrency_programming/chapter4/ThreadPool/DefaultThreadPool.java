package com.masonluo.the_art_of_java_concurrency_programming.chapter4.ThreadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;

    // 线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    // 线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;

    // 工作列表，将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<>();

    // 工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    // 工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    // 线程编号的形成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUMBERS? MAX_WORKER_NUMBERS: num < MIN_WORKER_NUMBERS
                ? MIN_WORKER_NUMBERS:num;
        initializeWorkers(workerNum);
    }

    @Override
    public void execute(Job job) {
        if(job != null){
            // 添加一个线程，进行通知
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }

        }
    }

    @Override
    public void shutdown() {
        for(Worker worker:workers){
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            // 限制新增的worker数量不能超过最大值
            if(num + this.workerNum > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum +=num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs){
            if(num > this.workerNum){
                throw new IllegalArgumentException("beyond workNum");
            }
            // 按照给定数量停止Worker
            int count = 0;
            while(count < num){
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -=count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }


    public void initializeWorkers(int workerNum){
        for(int i = 0; i < workerNum; i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    class Worker implements Runnable {

        // 是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    // 如果工作者列表是空的，那么等待
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 取出一个Job
                    job = jobs.removeFirst();
                }
                if(job != null){
                    try{
                        job.run();
                    }catch (Exception e){
                        // 忽略Job执行中的exception
                    }
                }
            }
        }

        public void shutdown(){
            running = false;
        }
    }
}
