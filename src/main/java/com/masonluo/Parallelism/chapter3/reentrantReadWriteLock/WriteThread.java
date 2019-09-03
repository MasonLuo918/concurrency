package com.masonluo.Parallelism.chapter3.reentrantReadWriteLock;

public class WriteThread extends Thread {
    int id;

    ReenTest test;

    int num;

    public WriteThread(int id, ReenTest test, int num){
        this.num = num;
        this.test = test;
        this.id = id;
    }

    @Override
    public void run(){
        for(int i = 0; i < num; i++){
            System.out.println("Thread " + id + "在写:");
            test.insert(id * num + i);
        }
    }
}
