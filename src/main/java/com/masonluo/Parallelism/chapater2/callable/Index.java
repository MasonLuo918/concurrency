package com.masonluo.Parallelism.chapater2.callable;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Index {

    public static final int N = 10000000;

    public static final int THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args){
        Integer[] array = new Integer[N];
        for(int i = 0; i < N; i++){
            array[i] = (int) (Math.random() * N);
        }
        int[] dataRange = new int[THREADS + 1];
        for(int i = 0; i < THREADS; i++){
            dataRange[i] = i * N / THREADS;
            if(dataRange[i] > N){
                dataRange[i] = N;
            }
        }
        Worker[] workers = new Worker[THREADS];
        System.out.println("产生" + THREADS + "个线程");
        List<FutureTask<Integer>> taskList = new ArrayList<>();
        for(int i = 0; i < THREADS; i++){
            workers[i] = new Worker(array, dataRange[i], dataRange[i + 1]);
            System.out.println("第" + i + "个线程将处理数据范围" + "[" + dataRange[i] + "," + dataRange[i + 1] + "]");
            FutureTask<Integer> task = new FutureTask<>(workers[i]);
            taskList.add(task);
            Thread t = new Thread(task);
            t.start();
        }
        Integer max = -1;
        for(FutureTask<Integer> task:taskList){
            Integer temp = null;
            try{
                temp = task.get();
            }catch (Exception e){
                e.printStackTrace();
            }
            if(max < temp){
                max = temp;
            }
        }
        System.out.println(max);
    }
}
