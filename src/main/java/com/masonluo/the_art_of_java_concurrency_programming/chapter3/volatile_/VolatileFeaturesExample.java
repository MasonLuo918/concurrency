package com.masonluo.the_art_of_java_concurrency_programming.chapter3.volatile_;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileFeaturesExample {
    volatile long v1 = 0L;

    public void set(long l){
        v1 = l;
    }

    public void getAndIncrement(){
        v1++;
    }

    public long get(){
        return v1;
    }
}
