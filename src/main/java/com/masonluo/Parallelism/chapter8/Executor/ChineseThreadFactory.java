package com.masonluo.Parallelism.chapter8.Executor;

import java.util.concurrent.ThreadFactory;

/**
 * Executor 一般使用的是默认的线程工厂，我们创建自定义的工厂
 */
public class ChineseThreadFactory implements ThreadFactory {
    private String name;

    private int threadNumber;

    ChineseThreadFactory(String name){
        this.name = name;
        threadNumber = 0;
    }

    @Override
    public Thread newThread(Runnable r) {
        String rName = name + "-线程" + threadNumber;
        Thread thread = new Thread(r, rName);
        System.out.println("新建了一个线程");
        threadNumber++;
        return thread;
    }
}
