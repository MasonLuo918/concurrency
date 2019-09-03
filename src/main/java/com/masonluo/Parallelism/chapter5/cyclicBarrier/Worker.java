package com.masonluo.Parallelism.chapter5.cyclicBarrier;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker extends Thread {
    int[] arr;

    CyclicBarrier barrier;

    public Worker(int[] array, CyclicBarrier barrier){
        this.arr = array;
        this.barrier = barrier;
    }

    @Override
    public void run(){
        Arrays.sort(arr);
        try{
            barrier.await();
        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}
