package com.masonluo.Parallelism.chapter3.reentrantReadWriteLock;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReenTest {
    private List<Integer> myList;

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    private Lock readLock = rwLock.readLock();

    private Lock writeLock = rwLock.writeLock();

    public ReenTest(List<Integer> myList){
        this.myList = myList;
    }

    public Integer get(int index){
        readLock.lock();
        try{
            return myList.get(index);
        }finally {
            readLock.unlock();
        }
    }

    public boolean insert(int newValue){
        writeLock.lock();
        try {
            return myList.add(newValue);
        }finally {
            writeLock.unlock();
        }
    }
}
