package com.masonluo.Parallelism.chapter8.ThreadFactory;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

public class Main {
    public static void main(String[] args){
        MyThreadFactory factory = new MyThreadFactory("线程工厂1");
        Worker worker = new Worker();
        Thread thread = factory.newThread(worker);
        thread.start();
    }
}
