package com.masonluo.Parallelism.chapater2.callable;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {
    Integer arr[];
    int begin;
    int end;

    public Worker(Integer[] arr, int begin, int end){
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        Integer max = arr[begin];
        for(int i = begin + 1; i < end; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }
}
