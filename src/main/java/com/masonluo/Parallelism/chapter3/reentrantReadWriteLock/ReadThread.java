package com.masonluo.Parallelism.chapter3.reentrantReadWriteLock;

public class ReadThread extends Thread {
    int id;

    ReenTest test;

    int num;

    public ReadThread(int id, ReenTest test, int num){
        this.id = id;
        this.num = num;
        this.test = test;
    }

    @Override
    public void run(){
        int index;
        for(int i = 0; i < num; i++){
            index = id * num + i;
            System.out.println("Thread "+ id + "在读:" + test.get(index));
        }
    }
}
