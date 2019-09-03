package com.masonluo.Parallelism.chapter7.example2;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args){
        int nthreads = Runtime.getRuntime().availableProcessors();
        int threshold = 100000;
        BigData bigData = new BigData(nthreads * threshold);
        Integer data[] =  bigData.getBigData();
        ForkJoinPool pool = new ForkJoinPool();
        MaxTask task = new MaxTask(data, 0, nthreads * threshold, threshold, nthreads);
        pool.invoke(task);
        try{
            System.out.println("最大值为" + task.get());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }
    }
}
