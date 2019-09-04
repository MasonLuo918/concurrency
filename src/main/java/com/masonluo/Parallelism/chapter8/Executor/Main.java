package com.masonluo.Parallelism.chapter8.Executor;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ChineseThreadFactory factory = new ChineseThreadFactory("chinese Factory");
        ExecutorService executorService = Executors.newCachedThreadPool(factory);
        Worker worker = new Worker();
        executorService.submit(worker);
        executorService.shutdown();
    }
}
