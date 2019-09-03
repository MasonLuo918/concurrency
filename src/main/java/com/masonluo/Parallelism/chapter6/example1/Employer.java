package com.masonluo.Parallelism.chapter6.example1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Employer {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public void dispatch(Worker worker){
        System.out.println("派遣工人");
        executor.execute(worker);
        System.out.println("活动线程数:" + executor.getActiveCount());
        System.out.println("线程池大小:" + executor.getPoolSize());
    }

    public void endWork(){
        executor.shutdown();
    }

    public static void main(String[] args){
        int workerNum = 5;
        Employer employer = new Employer();
        for(int i = 0; i < workerNum; i++){
            Worker worker = new Worker("工人" + i);
            employer.dispatch(worker);
        }
        employer.endWork();
    }
}
